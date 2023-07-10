package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.dto.DrinkInfoWithScrapStatus;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;

import java.util.List;

/**
 * 음료 정보 조회 Interface
 */
public interface FindDrinkRepository {
    DrinkEntity findDrinkById(final Long drinkId);

    List<DrinkInfoWithScrapStatus> findDrinkDetail(final Long memberId, final Long franchiseId, final String drinkName);

    void checkDrinkExist(final Long franchiseId, final String drinkName);
}
