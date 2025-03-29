package com.example.filtering.csvread.service;

import com.example.filtering.hyo.entity.ShoppingMallStatus;
import com.example.filtering.hyo.repository.ShoppingMallStatusRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class EachDataInserter {

    private final ShoppingMallStatusRepository repository;

    public void insertFromCsv(String csvPath) {
        try (Reader in = new FileReader(csvPath)) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (CSVRecord record : records) {
                ShoppingMallStatus entity = new ShoppingMallStatus(
                        getSafe(record, "shop_name"),
                        parseIntSafe(record, "all_rating"),
                        getSafe(record, "shop_status"),
                        parseDateSafe(record, "monitoring_day")
                );
                repository.save(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int parseIntSafe(CSVRecord record, String key) {
        try {
            String value = record.get(key);
            return (value != null && !value.isBlank()) ? Integer.parseInt(value) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private String getSafe(CSVRecord record, String key) {
        return (record.isMapped(key) && record.get(key) != null) ? record.get(key) : "";
    }

    private LocalDate parseDateSafe(CSVRecord record, String key) {
        try {
            String value = record.get(key);
            if (value == null || value.isBlank()) return null;

            // "2024-03-15" 또는 "2024-03-15 00:00:00" 등의 형식 처리
            if (value.contains(" ")) {
                return LocalDate.parse(value.split(" ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        } catch (Exception e) {
            return null;
        }
    }
}