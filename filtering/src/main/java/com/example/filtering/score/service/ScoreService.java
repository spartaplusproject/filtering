package com.example.filtering.score.service;

import com.example.filtering.score.dto.ScoreResponseDto;
import com.example.filtering.score.entity.Score;
import com.example.filtering.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    @Transactional(readOnly = true)
    public List<ScoreResponseDto> getScoresByValue(int score) {
        List<Score> filterScore =  scoreRepository.findTop10ByScoreOrderByCompanyNameAsc(Long.valueOf(score));

        List<ScoreResponseDto> dtos = new ArrayList<>();
        for (Score OneScore : filterScore) {
            dtos.add(new ScoreResponseDto(OneScore.getCompanyName(), OneScore.getScore()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<ScoreResponseDto> getAllScores() {
        List<Score> AllScores = scoreRepository.findAll();

        List<ScoreResponseDto> dtos = new ArrayList<>();
        for (Score OneScore : AllScores) {
            dtos.add(new ScoreResponseDto(OneScore.getCompanyName(), OneScore.getScore()));
        }
        return dtos;
    }
}
