package com.example.filtering.score.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "seoul_internet_shopping_mall_status")
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "all_rating")
    private Long score;
}
