package com.depromeet.oversweet.drink.vo;

import com.depromeet.oversweet.common.vo.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 음료 디테일 정보 DTO
 */
public record DrinkDailyDetailInfo(
        @Schema(description = "음료 ID", example = "1") Long id,

        @Schema(description = "음료 이름", example = "아메리카노") String name,

        // TODO FranchiseInfo 같이 사용하는 부분 하나로 쓰기
        @Schema(description = "음료 프랜차이즈 정보") FranchiseInfo franchise,

        @Schema(description = "음료 이미지 URL") String drinkImageUrl,

        @Schema(description = "음료 사이즈", example = "355") int size,

        @Schema(description = "음료 칼로리", example = "100") int calorie,

        @Schema(description = "음료 당도", example = "10") int sugar
) {
    public static DrinkDailyDetailInfo of(final DrinkEntity drink){
        return new DrinkDailyDetailInfo(
                drink.getId(),
                drink.getName(),
                FranchiseInfo.of(drink.getFranchise()),
                drink.getImageUrl(),
                drink.getSize(),
                drink.getCalorie(),
                drink.getSugar()
        );
    }
}
