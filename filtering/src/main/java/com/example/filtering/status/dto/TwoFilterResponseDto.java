package com.example.filtering.status.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class TwoFilterResponseDto {
    private final String companyName;
    private final String shopStatus;
    private final LocalDate monitoringDay;
    private final int score;

    public TwoFilterResponseDto(String companyName, String shopStatus, LocalDate monitoringDay, int score) {
        this.companyName = companyName;
        this.shopStatus = shopStatus;
        this.monitoringDay = monitoringDay;
        this.score = score;
    }
}
