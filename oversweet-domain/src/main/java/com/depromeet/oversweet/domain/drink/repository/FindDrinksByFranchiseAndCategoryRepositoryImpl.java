package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
public class FindDrinksByFranchiseAndCategoryRepositoryImpl implements FindDrinksByFranchiseAndCategoryRepository {

    private final JPAQueryFactory queryFactory;

    public FindDrinksByFranchiseAndCategoryRepositoryImpl(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<DrinkEntity>> findDrinksByFranchiseAndCategoryAndDirection(Long franchiseId, String category, String column, String direction) {
        return queryFactory.selectFrom(drinkEntity)
                .join(drinkEntity.franchise, franchiseEntity).fetchJoin()
                .where(franchiseIdEq(franchiseId), categoryEq(category))
                .orderBy(getOrderSpecifier(column, direction))
                .distinct()
                .transform(groupBy(drinkEntity.franchise.id).as(list(drinkEntity)));
    }

    private BooleanExpression franchiseIdEq(Long franchiseId) {
        return Objects.isNull(franchiseId) ? null : drinkEntity.franchise.id.eq(franchiseId);
    }

    private BooleanExpression categoryEq(String category) {
        return Objects.isNull(category) ? null : drinkEntity.category.eq(DrinkCategory.valueOf(category));
    }

    private static OrderSpecifier<?> getOrderSpecifier(String column, String direction) {
        OrderSpecifier<?> orderSpecifier = null;

        Order order = direction.equalsIgnoreCase("DESC") ? Order.DESC : Order.ASC;

        switch (column) {
            case "sugar":
                orderSpecifier = new OrderSpecifier<>(order, drinkEntity.sugar);
                break;
            default:
                break;
        }
        return orderSpecifier;
    }

}
