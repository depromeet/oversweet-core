package com.depromeet.oversweet.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record DrinkDailySugarStatisticsResponse(
        @Schema(description = "하루 음료 당 통계")
        DrinkDailySugarTotalStatisticsInfo totalDailyStatistics,

        @Schema(description = "오늘 마신 음료 목록")
        List<DrinkDailyDetailInfo> dailyDrinks
) {
}
