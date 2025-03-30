package com.example.filtering.status.dto;

import lombok.Getter;

@Getter
public class StatusResponseDto {
    private final String companyName;
    private final String shopStatus;

    public StatusResponseDto(String companyName, String shopStatus) {
        this.companyName = companyName;
        this.shopStatus = shopStatus;
    }
}
