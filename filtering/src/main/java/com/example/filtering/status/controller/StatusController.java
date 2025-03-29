package com.example.filtering.status.controller;

import com.example.filtering.status.dto.StatusResponseDto;
import com.example.filtering.status.dto.TwoFilterResponseDto;
import com.example.filtering.status.entity.Status;
import com.example.filtering.status.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    //업체 상태 필터링
    @GetMapping("/stores/status")
    public List<StatusResponseDto> getFilterStatus (@RequestParam(required = false, name = "status") String status) {
        return statusService.getStatus(status);
    }

    //전체평가 와 업체 상태 둘 다 합쳐서 필터링...모니터링 날짜 기준 조회
    @GetMapping("/stores/two-filter")
    public List<TwoFilterResponseDto> filteredStores (@RequestParam(required = false, name = "score") int score,
                                                      @RequestParam(required = false, name = "status") String status ) {
        return statusService.getFilteredStores(score, status);
    }
}
