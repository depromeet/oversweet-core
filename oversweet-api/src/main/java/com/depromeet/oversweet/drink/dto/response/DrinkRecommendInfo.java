package com.depromeet.oversweet.drink.dto.response;

import com.depromeet.oversweet.drink.vo.SugarDiffState;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class DrinkRecommendInfo {

    @Schema(description = "음료 id")
    private final Long id;

    @Schema(description = "음료 이름")
    private final String name;

    @Schema(description = "음료 이미지 url")
    private final String imageUrl;

    @Schema(description = "당 성분 차이")
    private final int sugarDifference;

    @Schema(description = "당 성분 차이 상태")
    private final SugarDiffState status;

    public DrinkRecommendInfo(final Long id, final String name, final String imageUrl, final int sugarDifference, final SugarDiffState status) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.sugarDifference = sugarDifference;
        this.status = status;
    }
}
