package com.example.filtering.csvread.service;

import com.example.filtering.csvread.util.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class BetterBatchInserter {

    public static void insertFromCsv() {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS seoul_internet_shopping_mall_status (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                shop_name TEXT,
                all_rating INT,
                shop_status TEXT,
                monitoring_day DATE,
                email TEXT,
                company_name TEXT
            );
        """;

        String insertQuery = """
            INSERT INTO seoul_internet_shopping_mall_status
            (shop_name, all_rating, shop_status, monitoring_day, email, company_name)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement tableStatement = connection.createStatement();
                Reader in = new FileReader("seoul_internet_shopping_mall_status.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)
        ) {
            tableStatement.execute(createTableQuery);
            connection.setAutoCommit(false);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            int batchSize = 500;
            int count = 0;

            for (CSVRecord record : records) {
                preparedStatement.setString(1, getSafe(record, "shop_name"));
                preparedStatement.setInt(2, parseIntSafe(record, "all_rating"));
                preparedStatement.setString(3, getSafe(record, "shop_status"));
                preparedStatement.setDate(4, parseDateSafe(record, "monitoring_day"));
                preparedStatement.setString(5, getSafe(record, "email"));
                preparedStatement.setString(6, getSafe(record, "company_name"));

                preparedStatement.addBatch();
                count++;

                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                    connection.commit();
                }
            }

            if (count % batchSize != 0) {
                preparedStatement.executeBatch();
                connection.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int parseIntSafe(CSVRecord record, String key) {
        try {
            String value = record.get(key);
            return (value != null && !value.isBlank()) ? Integer.parseInt(value) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static String getSafe(CSVRecord record, String key) {
        return (record.isMapped(key) && record.get(key) != null) ? record.get(key) : "";
    }

    private static java.sql.Date parseDateSafe(CSVRecord record, String key) {
        try {
            String value = record.get(key);
            if (value == null || value.isBlank()) return null;
            if (value.contains(" ")) {
                value = value.split(" ")[0];
            }
            LocalDate date = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
    }
}
