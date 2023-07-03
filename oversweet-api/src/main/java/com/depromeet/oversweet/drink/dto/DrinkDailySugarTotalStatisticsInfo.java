package com.depromeet.oversweet.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * 유저가 먹은 음료 당, 칼로리 통계 정보 DTO
 */
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
        return new DrinkDailySugarTotalStatisticsInfo(dailySugar, checkRemainingSugar(remainingDailySugar), dailyTotalSugar, dailyTotalCalorie, isSugarExceeded(dailyTotalSugar, dailySugar));
    }

    private static int checkRemainingSugar(final int remainingDailySugar) {
        if (remainingDailySugar >= 0) {
            return remainingDailySugar;
        }
        return remainingDailySugar * -1;
    }

    private static boolean isSugarExceeded(final int dailyTotalSugar, final int dailySugar) {
        return (dailySugar - dailyTotalSugar) < 0;
    }
}
