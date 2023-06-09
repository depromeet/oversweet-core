package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;

import java.util.List;

/**
 * 해당 프랜차이즈의 음료 목록을 키워드로 조회하는 Repository
 */
public interface FindDrinksSearchRepository {
    List<DrinkEntity> findDrinksByFranchiseAndKeyword(final Long franchiseId, final String keyword);
    List<DrinkEntity> findDrinksByKeyword(final String keyword);
}
