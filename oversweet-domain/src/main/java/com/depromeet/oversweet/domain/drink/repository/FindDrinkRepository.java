package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;

/**
 * 음료 정보 조회 Interface
 */
public interface FindDrinkRepository {
    DrinkEntity findDrinkById(final Long drinkId);
}
