package com.example.filtering.hyo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "seoul_internet_shopping_mall_status")
public class ShoppingMallStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "all_rating")
    private int allRating;
    @Column(name = "shop_status")
    private String shopStatus;
    @Column(name = "monitoring_day")
    private LocalDateTime monitoringDay;
    @Column(name = "email")
    private String email;

    public ShoppingMallStatus(String shopName, int allRating, String shopStatus, LocalDateTime monitoringDay) {
        this.shopName = shopName;
        this.allRating = allRating;
        this.shopStatus = shopStatus;
        this.monitoringDay = monitoringDay;
    }

    public ShoppingMallStatus(String email) {
        this.email = email;
    }
}
