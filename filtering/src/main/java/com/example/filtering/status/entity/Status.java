package com.example.filtering.status.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "seoul_internet_shopping_mall_status")
public class Status {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "shop_status")
    private String shopStatus;

    @Column(name = "monitoring_day")
    private LocalDate monitoringDay;

    @Column(name = "all_rating")
    private int score;

}
