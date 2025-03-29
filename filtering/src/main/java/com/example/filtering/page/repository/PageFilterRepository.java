package com.example.filtering.page.repository;

import com.example.filtering.page.dto.PageFilterResponseDto;
import com.example.filtering.page.entity.PageFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageFilterRepository extends JpaRepository<PageFilter, Long> {
    Page<PageFilterResponseDto> findByScoreAndShopStatus(int score, String shopStatus, Pageable pageable);
}
