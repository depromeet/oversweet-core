package com.depromeet.oversweet.drink.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DrinkDailySugarInfo(
        @Schema(description = "해당 일")
        LocalDate day,
        @Schema(description = "해당 요일에 먹은 당 섭취량")
        int sugar
) {
}
