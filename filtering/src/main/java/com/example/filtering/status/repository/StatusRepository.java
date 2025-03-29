package com.example.filtering.status.repository;

import com.example.filtering.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    //업체 상태 -- 회사 이름 순으로 상위 10개 조회
    List<Status> findTop10ByShopStatusOrderByCompanyNameAsc(String status);

    //전체 평가 필터 와 업체 상태 필터 2개 동시에 적용하기(회사명 기준으로 상위 10개)
    List<Status> findTop10ByScoreAndShopStatusOrderByMonitoringDayDesc(int score, String shopStatus);
}
