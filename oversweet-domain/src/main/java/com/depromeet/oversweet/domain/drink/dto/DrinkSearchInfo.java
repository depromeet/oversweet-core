package com.depromeet.oversweet.domain.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class DrinkSearchInfo {

    @Schema(name = "음료 ID", example = "1")
    private Long id;

    @Schema(name = "음료 이름", example = "아메리카노")
    private String name;

    @Schema(name = "음료 이미지 URL")
    private String imageUrl;

    @Schema(name = "음료 사이즈 목록")
    private List<Integer> size;

    @Schema(name = "프랜차이즈 정보")
    private FranchiseInfo franchise;

    @Schema(name = "음료 칼로리", example = "10")
    private Integer calorie;

    @Schema(name = "음료 당도", example = "10")
    private Integer sugar;

    public DrinkSearchInfo(Long id, String name, String imageUrl, List<Integer> size,
                           Long franchiseId, String franchiseName, String franchiseImageUrl,
                           Integer calorie, Integer sugar) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.size = size;
        this.franchise = new FranchiseInfo(franchiseId, franchiseName, franchiseImageUrl);
        this.calorie = calorie;
        this.sugar = sugar;
    }
}
