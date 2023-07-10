package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.dto.DrinkSimpleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 필요한 음료의 정보를 토대로 모든 음료를 가져오는 Repository 구현체
 */
@Repository
@RequiredArgsConstructor
public class FindAllDrinkRepositoryImpl implements FindAllDrinkRepository{

    private final DrinkJpaRepository drinkJpaRepository;

    @Override
    public List<DrinkSimpleInfo> findAll() {
        return drinkJpaRepository.findAllDrinkSimpleInfo();
    }
}
