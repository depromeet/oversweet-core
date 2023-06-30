package com.depromeet.oversweet.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record DrinkDailySugarTotalStatisticsInfo(

        @Schema(description = "하루 적정 당 섭취량", example = "25")
        int dailySugar,

        @Schema(description = "남은 하루 적정 당 섭취량", example = "5")
        int remainingDailySugar,

        @Schema(description = "오늘 먹은 총 당 섭취량", example = "15")
        int dailyTotalSugar,

        @Schema(description = "오늘 먹은 총 당 칼로리", example = "300")
        int dailyTotalCalorie,

        @Schema(description = "적정 당 섭취량을 초과 했는지 안했는지 여부", example = "false")
        boolean isExcess
) {
    public static DrinkDailySugarTotalStatisticsInfo of(final int dailySugar, final int remainingDailySugar, final int dailyTotalSugar, final int dailyTotalCalorie) {
        return new DrinkDailySugarTotalStatisticsInfo(dailySugar, remainingDailySugar, dailyTotalSugar, dailyTotalCalorie, -1 == remainingDailySugar);
    }
}
