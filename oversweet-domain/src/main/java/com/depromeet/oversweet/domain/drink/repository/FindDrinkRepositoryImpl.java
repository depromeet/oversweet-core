package com.depromeet.oversweet.domain.drink.repository;

import static com.depromeet.oversweet.exception.ErrorCode.NOT_FOUND_DRINK;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.oversweet.domain.drink.dto.DrinkInfoWithScrapStatus;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.exception.drink.NotFoundDrinkException;

import lombok.RequiredArgsConstructor;

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

    /**
     * 음료 상세 정보 조회
     * 프랜차이즈 정보 및 즐겨 찾기 여부를 함께 조회한다.
     *
     * @param franchiseId 상세 보기를 원하는 음료의 프랜차이즈 ID
     * @param drinkName   상세 보기를 원하는 음료의 이름
     */
    @Override
    @Transactional(readOnly = true)
    public List<DrinkInfoWithScrapStatus> findDrinkDetail(final Long memberId, final Long franchiseId, final String drinkName) {
        return drinkJpaRepository.findDrinkWithBookmarkStatus(memberId, franchiseId, drinkName);
    }

    /**
     * 음료가 존재하는지 확인
     *
     * @param franchiseId 음료의 프랜차이즈 ID
     * @param drinkName   음료 이름
     */
    @Override
    @Transactional(readOnly = true)
    public void checkDrinkExist(final Long franchiseId, final String drinkName) {
        if (drinkJpaRepository.findByFranchiseIdAndName(franchiseId, drinkName)
                .isEmpty()) {
            throw new NotFoundDrinkException(NOT_FOUND_DRINK);
        }
    }

    /**
     * 당 성분이 비슷한 음료 추천
     * top 30을 찾고 랜덤으로 섞어서 5개를 추천한다.
     *
     * @param minSugar 최소 당 성분
     * @param maxSugar 최대 당 성분
     * @return 당 성분이 비슷한 음료 5개
     */
    @Override
    @Transactional(readOnly = true)
    public List<DrinkEntity> findDrinkBetweenSugar(final int minSugar, final int maxSugar) {
        // 비슷한 음료 30개 정도를 조회한고 나서 랜덤으로 섞는다.
        List<DrinkEntity> drinks = drinkJpaRepository.findTop30BySugarBetween(minSugar, maxSugar);
        Collections.shuffle(drinks);

        // 비슷한 음료가 5개보다 적은 경우
        int count = Math.min(drinks.size(), 5);

        return drinks.subList(0, count);
    }

}
