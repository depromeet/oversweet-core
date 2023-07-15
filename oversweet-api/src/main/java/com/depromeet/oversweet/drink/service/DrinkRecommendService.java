package com.depromeet.oversweet.drink.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.drink.repository.FindDrinkRepository;
import com.depromeet.oversweet.drink.dto.response.DrinkRecommendResponse;

import lombok.RequiredArgsConstructor;

/**
 * 음료 추천 Service
 */
@Service
@RequiredArgsConstructor
public class DrinkRecommendService {

    private final FindDrinkRepository findDrinkRepository;

    /**
     * 당 성분이 비슷한 음료 추천
     *
     * @param drinkId 음료 id
     * @return 추천 음료 리스트
     */
    public DrinkRecommendResponse recommendDrink(final Long drinkId) {
        // 음료 조회
        DrinkEntity drinkEntity = findDrinkRepository.findDrinkById(drinkId);

        // 음료 당 성분 기준 +-5 범위의 당 추출
        int minSugar = drinkEntity.getMinSugarForRecommend();
        int maxSugar = drinkEntity.getMaxSugarForRecommend();

        // 조회된 음료의 프랜차이즈 and 당 추출이 +-5 범위 내의 음료 조회
        List<DrinkEntity> recommendDrinks = findDrinkRepository.findDrinkBetweenSugar(minSugar, maxSugar);

        return DrinkRecommendResponse.from(drinkEntity.getSugar(), recommendDrinks);
    }
}
