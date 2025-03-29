package com.example.filtering.status.service;

import com.example.filtering.status.dto.StatusResponseDto;
import com.example.filtering.status.dto.TwoFilterResponseDto;
import com.example.filtering.status.entity.Status;
import com.example.filtering.status.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;
    
    @Transactional(readOnly = true)
    //업소 상태 필터링
    public List<StatusResponseDto> getStatus(String status) {
        List<Status> filterStatus = statusRepository.findTop10ByShopStatusOrderByCompanyNameAsc(status);
        
        List<StatusResponseDto> dtos = new ArrayList<>();
        for (Status shopStatus : filterStatus) {
            dtos.add(new StatusResponseDto(shopStatus.getCompanyName(), shopStatus.getShopStatus()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    //전체평가 와 업체 상태 둘 다 합쳐서 필터링...모니터링 날짜 기준 조회
    public List<TwoFilterResponseDto> getFilteredStores(int score, String status) {
        List<Status> twofilters = statusRepository.findTop10ByScoreAndShopStatusOrderByMonitoringDayDesc(score, status);

        List<TwoFilterResponseDto> dtos = new ArrayList<>();
        for (Status twofilter : twofilters) {
            dtos.add(new TwoFilterResponseDto(twofilter.getCompanyName(),twofilter.getShopStatus(),
                    twofilter.getMonitoringDay(), twofilter.getScore()));
        }
        return dtos;
    }
}
