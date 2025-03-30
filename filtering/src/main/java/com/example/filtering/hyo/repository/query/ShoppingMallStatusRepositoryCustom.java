package com.example.filtering.hyo.repository.query;

import com.example.filtering.hyo.entity.ShoppingMallStatus;

import java.util.List;

public interface ShoppingMallStatusRepositoryCustom {
    List<ShoppingMallStatus> findShopWithCursor(Long lastId, int rating, String status, int limit);
}
