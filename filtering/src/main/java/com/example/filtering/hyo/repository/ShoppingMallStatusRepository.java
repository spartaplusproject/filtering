package com.example.filtering.hyo.repository;

import com.example.filtering.hyo.entity.ShoppingMallStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingMallStatusRepository extends JpaRepository<ShoppingMallStatus, Long> {

    //전체평가 내림차순 조회
    List<ShoppingMallStatus> findTop10ByAllRatingOrderByIdDesc(int allRating);

    //업소상태 내림차순 조회
    List<ShoppingMallStatus> findTop10ByShopStatusOrderByIdDesc(String shopStatus);

    //전체평가, 업소상태, 모니터링 날짜 기준 내림차순 조회
    List<ShoppingMallStatus> findTop10ByAllRatingAndShopStatusOrderByMonitoringDayDesc(int allRating, String shopStatus);

    //Pageable 기반 필터 조회
    Page<ShoppingMallStatus> findByAllRatingAndShopStatus(int allRating, String shopStatus, Pageable pageable);
}
