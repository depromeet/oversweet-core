package com.depromeet.oversweet.record.dto.response;

import com.depromeet.oversweet.drink.dto.response.DrinkDailySugarTotalStatisticsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DrinkRecordSaveResponse(

        @Schema(description = "지금 마신 음료 당", example = "10") int intakeSugar,

        @Schema(description = "하루 적정 당 섭취량", example = "24") int dailySugar,

        @Schema(description = "방금 기록한 당 성분까지 포함한, 오늘 먹은 총 당 섭취량", example = "20") int dailyTotalSugar,

        @Schema(description = "적정 당 섭취량을 초과 했는지 안했는지 여부", example = "false") boolean isExcess

) {
    public static DrinkRecordSaveResponse of(int intakeSugar, DrinkDailySugarTotalStatisticsInfo info) {
        return new DrinkRecordSaveResponse(
                intakeSugar, info.dailySugar(), info.dailyTotalSugar(), info.isExcess()
        );
    }
}
