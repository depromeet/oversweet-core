package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.dto.DrinkSimpleInfo;

import java.util.List;

/**
 * 필요한 음료의 정보를 토대로 모든 음료를 가져오는 Repository 인터페이스
 */
public interface FindAllDrinkRepository {
    List<DrinkSimpleInfo> findAll();
}
