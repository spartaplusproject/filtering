package com.example.filtering.hyo.controller;

import com.example.filtering.hyo.entity.ShoppingMallStatus;
import com.example.filtering.hyo.service.ShoppingMallStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping-malls")
public class ShoppingMallStatusControllerV1 {

    private final ShoppingMallStatusService shopService;

    //--------필수 구현 기능--------

    //전체평가 필터
    @GetMapping("/all-rating")
    public List<ShoppingMallStatus> findShopByAllRating(
            @RequestParam(name = "allRating") int allRating) {
        return shopService.findShopByAllRating(allRating);
    }
    //업소상태 필터
    @GetMapping("/shop-status")
    public List<ShoppingMallStatus> findShopByShopStatus(
            @RequestParam(name = "shopStatus") String shopStatus) {
        return shopService.findShopByShopStatus(shopStatus);
    }

    //둘 다 합쳐서 모니터링 날짜 기준 내림차순 조회
    @GetMapping("/all-filter")
    public List<ShoppingMallStatus> filteredShops(
            @RequestParam(name = "allRating") int allRating,
            @RequestParam(name = "shopStatus") String shopStatus) {
        return shopService.allFilteredShops(allRating, shopStatus);
    }

    //--------선택 구현 기능--------

    @GetMapping("/page-filtered")
    public Page<ShoppingMallStatus> pageableShops(
            @RequestParam(name = "allRating") int allRating,
            @RequestParam(name = "shopStatus") String shopStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return shopService.getFilteredShops(allRating, shopStatus, page, size);
    }

    //--------심화 구현 기능--------
    @GetMapping("/query-filtered")
    public List<ShoppingMallStatus> getFilteredShops(
            @RequestParam(name = "lastId", required = false) Long lastId,
            @RequestParam(name = "allRating",defaultValue = "0") int allRating,
            @RequestParam(name = "shopStatus") String shopStatus) {
        return shopService.getFilteredShops(lastId, allRating, shopStatus);
    }
}
