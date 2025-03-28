package com.example.filtering.score.dto;

import lombok.Getter;

@Getter
public class ScoreResponseDto {
    private final String companyName;
    private final Long score;

    public ScoreResponseDto(String companyName, Long score) {
        this.companyName = companyName;
        this.score = score;
    }
}
