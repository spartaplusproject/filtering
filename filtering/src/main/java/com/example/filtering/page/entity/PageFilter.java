package com.example.filtering.page.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "seoul_internet_shopping_mall_status")
public class PageFilter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "all_rating")
    private int score;

    @Column(name = "shop_status")
    private String shopStatus;

    @Column(name = "email")
    private String email;


}
