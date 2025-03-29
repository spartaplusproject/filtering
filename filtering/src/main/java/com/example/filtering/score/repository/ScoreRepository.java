package com.example.filtering.score.repository;

import com.example.filtering.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    //전체 평가 회사명에 따라 상위 10개만 조회
    List<Score> findTop10ByScoreOrderByCompanyNameAsc(Long score);
}
