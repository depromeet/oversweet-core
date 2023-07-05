package com.depromeet.oversweet.drink.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 유저의 주간 당 섭취 정보 통계 VO
 */
public record DrinkWeeklySugarTotalStatisticsInfo(
        @Schema(description = "유저의 총 주간 당 섭취량", example = "500")
        int totalSugar,
        @Schema(description = "유저의 하루 평균 당 섭취량", example = "30")
        int dailyAverageSugar,
        @Schema(description = "유저의 총 주간 칼로리 섭취량", example = "1230")
        int totalCalorie
) {
}
