package com.example.filtering.hyo.repository.query;

import com.example.filtering.hyo.entity.QShoppingMallStatus;
import com.example.filtering.hyo.entity.ShoppingMallStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingMallStatusRepositoryCustomImpl implements ShoppingMallStatusRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public ShoppingMallStatusRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ShoppingMallStatus> findShopWithCursor(Long lastId, int rating, String status, int limit) {
        QShoppingMallStatus shop = QShoppingMallStatus.shoppingMallStatus;

        return queryFactory
                .selectFrom(shop)
                .where(
                        shop.allRating.eq(rating),
                        shop.shopStatus.eq(status),
                        lastId != null ? shop.id.lt(lastId) : null // lt은 less than으로 입력한 값보다 작은 데이터를 조회한다.
                )
                .orderBy(shop.id.desc())
                .limit(limit)
                .fetch();
    }
}
