package com.example.filtering.page.service;

import com.example.filtering.page.dto.PageFilterResponseDto;
import com.example.filtering.page.entity.PageFilter;
import com.example.filtering.page.repository.PageFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageFilterService {
    private final PageFilterRepository pageFilterRepository;


    public Page<PageFilterResponseDto> getFilteredPage(int score, String status, int page, int size) {
        return pageFilterRepository.findByScoreAndShopStatus(score, status, PageRequest.of(page, size));

    }
}
