package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.exception.drink.NotFoundDrinkException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_DRINK;

/**
 * 음료 정보 조회 Interface 구현체
 */
@Repository
@RequiredArgsConstructor
public class FindDrinkRepositoryImpl implements FindDrinkRepository {

    private final DrinkJpaRepository drinkJpaRepository;

    /**
     * 음료 정보 조회
     *
     * @param drinkId 음료 id
     * @return 음료 Entity
     */
    @Override
    @Transactional(readOnly = true)
    public DrinkEntity findDrinkById(final Long drinkId) {
        return drinkJpaRepository.findById(drinkId)
                .orElseThrow(() -> new NotFoundDrinkException(NOT_FOUND_DRINK));
    }
}
