package com.depromeet.oversweet.drink.dto.response;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.drink.vo.SugarDiffState;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class DrinkRecommendResponse {

    @Schema(description = "추천 음료 리스트")
    private final List<DrinkRecommendInfo> drinks;

    private DrinkRecommendResponse(final List<DrinkRecommendInfo> drinks) {
        this.drinks = drinks;
    }

    public static DrinkRecommendResponse from(int mainSugar, List<DrinkEntity> recommendDrinks) {
        List<DrinkRecommendInfo> recommendInfo = recommendDrinks.stream()
                .map(drink -> {
                    int diff = Math.abs(mainSugar - drink.getSugar());
                    SugarDiffState state = SugarDiffState.from(mainSugar, drink.getSugar());
                    return new DrinkRecommendInfo(drink.getId(), drink.getName(), drink.getImageUrl(), diff, state);
                })
                .collect(toList());

        return new DrinkRecommendResponse(recommendInfo);
    }

}
