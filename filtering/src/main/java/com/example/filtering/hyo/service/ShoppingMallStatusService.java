package com.example.filtering.hyo.service;

import com.example.filtering.hyo.entity.ShoppingMallStatus;
import com.example.filtering.hyo.repository.ShoppingMallStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingMallStatusService {

    private final ShoppingMallStatusRepository shopRepository;

    // 전체평가 내림차순 조회
    public List<ShoppingMallStatus> findShopByAllRating(int allRating) {
        return shopRepository.findTop10ByAllRatingOrderByIdDesc(allRating);
    }

    // 업소상태 내림차순 조회
    public List<ShoppingMallStatus> findShopByShopStatus(String shopStatus) {
        return shopRepository.findTop10ByShopStatusOrderByIdDesc(shopStatus);
    }

    //전체평가, 업소상태, 모니터링 날짜 기준 내림차순 조회
    public List<ShoppingMallStatus> allFilteredShops(int allRating, String shopStatus) {
        return shopRepository.findTop10ByAllRatingAndShopStatusOrderByMonitoringDayDesc(allRating, shopStatus);
    }

    public Page<ShoppingMallStatus> getFilteredShops(int allRating, String shopStatus, int page, int size) {
        return shopRepository.findByAllRatingAndShopStatus(allRating, shopStatus, PageRequest.of(page, size));
    }

}
