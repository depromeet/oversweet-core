package com.depromeet.oversweet.search.dto.response;

import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DrinkAllInfoResponse(
        @Schema(name = "음료 ID", example = "1")
        Long id,
        @Schema(name = "음료 이름", example = "아메리카노")
        String name,
        @Schema(name = "음료 이미지 URL")
        String imageUrl,
        @Schema(name = "음료 사이즈 목록")
        List<Integer> size,
        @Schema(name = "프랜차이즈 정보")
        FranchiseInfo franchise,
        @Schema(name = "음료 칼로리", example = "10")
        Integer calorie,
        @Schema(name = "음료 당도", example = "10")
        Integer sugar
) {
    public static DrinkAllInfoResponse of(final DrinkEntity drinkEntity, final List<Integer> sizes) {
        return new DrinkAllInfoResponse(drinkEntity.getId(), drinkEntity.getName(), drinkEntity.getImageUrl(),
                sizes, FranchiseInfo.of(drinkEntity.getFranchise()), drinkEntity.getCalorie(), drinkEntity.getSugar());
    }
}
