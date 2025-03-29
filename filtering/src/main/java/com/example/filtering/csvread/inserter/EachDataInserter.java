package com.example.filtering.csvread.inserter;

import com.example.filtering.csvread.util.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EachDataInserter {
    public static void insertData() {
        //테이블이 존재하지 않는다면 사용하는 테이블 생성 쿼리
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS seoul_internet_shopping_mall_status (
                company_name TEXT NULL,
                shop_name TEXT NULL,
                domain_name TEXT NULL,
                phone_number TEXT NULL,
                email TEXT NULL,
                mail_order_number TEXT NULL,
                business_type TEXT NULL,
                first_report_date TEXT NULL,
                address TEXT NULL,
                shop_status TEXT NULL,
                all_rating INT NULL,
                information_rating INT NULL,
                cooling_off_rating INT NULL,
                payment_method_rating INT NULL,
                terms_of_use_rating INT NULL,
                security_rating INT NULL,
                main_items TEXT NULL,
                cooling_off_availability TEXT NULL,
                initial_required_item TEXT NULL,
                payment_method TEXT NULL,
                degree_of_compliance_terms_of_use TEXT NULL,
                handling_personal_information TEXT NULL,
                requires_information_above_standard TEXT NULL,
                buying_security_service TEXT NULL,
                secure_server_installation TEXT NULL,
                certification_mark TEXT NULL,
                delivery_date_mark TEXT NULL,
                cancel_delivery_charge_burden TEXT NULL,
                board_operation TEXT NULL,
                withdraw_method TEXT NULL,
                website_open_year TEXT NULL,
                monitoring_day TEXT NULL
            );
        """;

        String insertQuery = "INSERT INTO seoul_internet_shopping_mall_status(company_name, shop_name, domain_name, phone_number, email, mail_order_number, business_type, first_report_date, address, shop_status, all_rating, information_rating, cooling_off_rating, payment_method_rating, terms_of_use_rating, security_rating, main_items, cooling_off_availability, initial_required_item, payment_method, degree_of_compliance_terms_of_use, handling_personal_information, requires_information_above_standard, buying_security_service, secure_server_installation, certification_mark, delivery_date_mark, cancel_delivery_charge_burden, board_operation, withdraw_method, website_open_year, monitoring_day) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement tableStatement = connection.createStatement();
             Reader in = new FileReader("seoul_internet_shopping_mall_status.csv");
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)
        ) {
            // 1. 테이블 먼저 생성
            tableStatement.execute(createTableQuery);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            connection.setAutoCommit(false);

            try {
                for (CSVRecord record : records) {
                    preparedStatement.setString(1, getSafe(record, "company_name"));
                    preparedStatement.setString(2, getSafe(record, "shop_name"));
                    preparedStatement.setString(3, getSafe(record, "domain_name"));
                    preparedStatement.setString(4, getSafe(record, "phone_number"));
                    preparedStatement.setString(5, getSafe(record, "email"));
                    preparedStatement.setString(6, getSafe(record, "mail_order_number"));
                    preparedStatement.setString(7, getSafe(record, "business_type"));
                    preparedStatement.setString(8, getSafe(record, "first_report_date"));
                    preparedStatement.setString(9, getSafe(record, "address"));
                    preparedStatement.setString(10, getSafe(record, "shop_status"));

                    preparedStatement.setInt(11, parseIntSafe(record, "all_rating"));
                    preparedStatement.setInt(12, parseIntSafe(record, "information_rating"));
                    preparedStatement.setInt(13, parseIntSafe(record, "cooling_off_rating"));
                    preparedStatement.setInt(14, parseIntSafe(record, "payment_method_rating"));
                    preparedStatement.setInt(15, parseIntSafe(record, "terms_of_use_rating"));
                    preparedStatement.setInt(16, parseIntSafe(record, "security_rating"));

                    preparedStatement.setString(17, getSafe(record, "main_items"));
                    preparedStatement.setString(18, getSafe(record, "cooling_off_availability"));
                    preparedStatement.setString(19, getSafe(record, "initial_required_item"));
                    preparedStatement.setString(20, getSafe(record, "payment_method"));
                    preparedStatement.setString(21, getSafe(record, "degree_of_compliance_terms_of_use"));
                    preparedStatement.setString(22, getSafe(record, "handling_personal_information"));
                    preparedStatement.setString(23, getSafe(record, "requires_information_above_standard"));
                    preparedStatement.setString(24, getSafe(record, "buying_security_service"));
                    preparedStatement.setString(25, getSafe(record, "secure_server_installation"));
                    preparedStatement.setString(26, getSafe(record, "certification_mark"));
                    preparedStatement.setString(27, getSafe(record, "delivery_date_mark"));
                    preparedStatement.setString(28, getSafe(record, "cancel_delivery_charge_burden"));
                    preparedStatement.setString(29, getSafe(record, "board_operation"));
                    preparedStatement.setString(30, getSafe(record, "withdraw_method"));
                    preparedStatement.setString(31, getSafe(record, "website_open_year"));
                    preparedStatement.setString(32, getSafe(record, "monitoring_day"));

                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();
                    connection.commit();
                }
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int parseIntSafe(CSVRecord record, String key) {
        if (!record.isMapped(key)) return 0;
        String value = record.get(key);
        return (value != null && !value.isBlank()) ? Integer.parseInt(value) : 0;
    }

    private static String getSafe(CSVRecord record, String key) {
        return (record.isMapped(key) && record.get(key) != null) ? record.get(key) : "";
    }
}
