package com.depromeet.oversweet.domain.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 음료 상세 정보를 보기 위한 응답 DTO
 */
@Getter
public class DrinkInfoWithScrapStatus {

    @Schema(name = "음료 ID", example = "1")
    private final Long id;
    @Schema(name = "음료 이름", example = "아메리카노")
    private final String name;
    @Schema(name = "음료 이미지 URL")
    private final String drinkImageUrl;
    @Schema(name = "음료 사이즈", example = "355")
    private final Integer size;
    @Schema(name = "음료 칼로리", example = "10")
    private final Integer calorie;
    @Schema(name = "음료 당도", example = "10")
    private final Integer sugar;
    @Schema(name = "즐겨찾기 여부", example = "true")
    private final Boolean scrapStatus;

    public DrinkInfoWithScrapStatus(Long id, String name, String drinkImageUrl, Integer size, Integer calorie, Integer sugar, Boolean scrapStatus) {
        this.id = id;
        this.name = name;
        this.drinkImageUrl = drinkImageUrl;
        this.size = size;
        this.calorie = calorie;
        this.sugar = sugar;
        this.scrapStatus = scrapStatus;
    }
}
