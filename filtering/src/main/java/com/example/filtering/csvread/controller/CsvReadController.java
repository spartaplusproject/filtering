package com.example.filtering.csvread.controller;

import com.example.filtering.csvread.service.BatchDataInserter;
import com.example.filtering.csvread.service.BetterBatchInserter;
import com.example.filtering.csvread.service.EachDataInserter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.standard.expression.Each;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CsvReadController {
    
    @PostMapping("/api/collection")
    public ResponseEntity<Map<String,String>> EachDateInsert() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        EachDataInserter.insertFromCsv();
        Map<String, String> message = new HashMap<>();
        message.put("message","데이터 1개씩 삽입 완료");
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/api/v2/collection")
    public ResponseEntity<Map<String, String>> BatchDateInsert() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        BatchDataInserter.insertFromCsv();
        Map<String, String> message = new HashMap<>();
        message.put("message","데이터 100개씩 삽입 완료");
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/api/v3/collection")
    public ResponseEntity<Map<String, String>> BetterBatchDateInsert() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        BetterBatchInserter.insertFromCsv();
        Map<String, String> message = new HashMap<>();
        message.put("message","데이터 500개씩 삽입 완료");
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}
