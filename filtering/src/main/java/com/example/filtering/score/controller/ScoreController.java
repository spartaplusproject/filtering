package com.example.filtering.score.controller;

import com.example.filtering.score.dto.ScoreResponseDto;
import com.example.filtering.score.entity.Score;
import com.example.filtering.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("/stores/total-scores")
    public List<ScoreResponseDto> getFilterScores(@RequestParam(required = false, name = "score") Integer score) {

        if (score != null) {
            return scoreService.getScoresByValue(score);
        } else {
            return scoreService.getAllScores();
        }

    }

}
