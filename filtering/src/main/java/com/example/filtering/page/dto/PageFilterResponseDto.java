package com.example.filtering.page.dto;

import lombok.Getter;

@Getter
public class PageFilterResponseDto {
    private final String companyName;
    private final int score;
    private final String shopStatus;


    public PageFilterResponseDto(String companyName, int score, String shopStatus) {
        this.companyName = companyName;
        this.score = score;
        this.shopStatus = shopStatus;
    }
}
