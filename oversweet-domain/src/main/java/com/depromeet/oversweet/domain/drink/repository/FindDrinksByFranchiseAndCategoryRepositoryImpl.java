package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.dto.DrinkSearchInfo;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;

@Repository
public class FindDrinksByFranchiseAndCategoryRepositoryImpl implements FindDrinksByFranchiseAndCategoryRepository {

    private final JPAQueryFactory queryFactory;

    public FindDrinksByFranchiseAndCategoryRepositoryImpl(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrinkEntity> findDrinksByFranchiseAndCategory(Long franchiseId, String category, Pageable pageable) {
        return queryFactory.selectFrom(drinkEntity)
                .join(drinkEntity.franchise, franchiseEntity).fetchJoin()
                .where(franchiseIdEq(franchiseId), categoryEq(category))
                .offset(pageable.getOffset()) // 페이지 번호
                .limit(pageable.getPageSize()) // 페이지 사이즈
                .fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DrinkSearchInfo> getDrinksByFranchiseAndCategoryWithPagination(List<DrinkSearchInfo> drinkSearchInfos, Long franchiseId, String category, Pageable pageable) {
        JPAQuery<Long> countQuery = queryFactory.select(drinkEntity.count())
                .from(drinkEntity)
                .join(drinkEntity.franchise, franchiseEntity).fetchJoin()
                .where(franchiseIdEq(franchiseId), categoryEq(category), drinkEntity.isMinimum.isTrue());

        return PageableExecutionUtils.getPage(drinkSearchInfos, pageable, () -> countQuery.fetchOne());
    }

    private BooleanExpression franchiseIdEq(Long franchiseId) {
        return Objects.isNull(franchiseId) ? null : drinkEntity.franchise.id.eq(franchiseId);
    }

    private BooleanExpression categoryEq(String category) {
        return Objects.isNull(category) ? null : drinkEntity.category.eq(DrinkCategory.valueOf(category));
    }

}
