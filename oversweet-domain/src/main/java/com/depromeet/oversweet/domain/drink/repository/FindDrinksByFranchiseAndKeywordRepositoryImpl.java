package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;

/**
 * 해당 프랜차이즈의 음료 목록을 키워드로 조회하는 Repository 구현벼
 */
@Repository
public class FindDrinksByFranchiseAndKeywordRepositoryImpl implements FindDrinksByFranchiseAndKeywordRepository {

    private final JPAQueryFactory queryFactory;

    public FindDrinksByFranchiseAndKeywordRepositoryImpl(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrinkEntity> findDrinksByFranchiseAndKeyword(final Long franchiseId, final String keyword) {
        return queryFactory.selectFrom(drinkEntity)
                .join(drinkEntity.franchise, franchiseEntity).fetchJoin()
                .where(drinkEntity.franchise.id.eq(franchiseId))
                .where(drinkEntity.name.contains(keyword))
                .fetch();
    }
}
