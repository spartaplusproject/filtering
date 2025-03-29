package com.example.filtering.page.controller;

import com.example.filtering.page.dto.PageFilterResponseDto;
import com.example.filtering.page.entity.PageFilter;
import com.example.filtering.page.service.PageFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PageFilterController {
    private final PageFilterService pageFilterService;

    @GetMapping("/stores/page-filtered")
    public Page<PageFilterResponseDto> pageableView (
            @RequestParam(required = false, name = "score") int score,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return pageFilterService.getFilteredPage(score, status, page, size);

    }
}
