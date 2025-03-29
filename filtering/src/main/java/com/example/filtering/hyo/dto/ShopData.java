package com.example.filtering.hyo.dto;

import lombok.Getter;

@Getter
public class ShopData {

    private Long id;
    private String shopName;
    private int allRating;

    public ShopData(Long id, String shopName, int allRating) {
        this.id = id;
        this.shopName = shopName;
        this.allRating = allRating;
    }
}
