package com.example.filtering.hyo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "download_shop_data")
public class DownloadShopData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // 상호
    private String shopName;    // 쇼핑몰명
    private String domainName;  // 도메인명
    private String phone;       // 전화번호
    private String email;       // 운영자 이메일
    private String shopStatus;  // 업소상태
}
