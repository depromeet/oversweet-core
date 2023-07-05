package com.depromeet.oversweet.drink.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DrinkDailySugarInfo{
    @Schema(description = "해당 일")
    @JsonProperty(value = "day")
    private LocalDate day;
    @Schema(description = "해당 요일에 먹은 당 섭취량")
    @JsonProperty(value = "sugar")
    private int sugar;

    private DrinkDailySugarInfo(final LocalDateTime day, final int sugar) {
        this.day = day.toLocalDate();
        this.sugar = sugar;
    }
    public static DrinkDailySugarInfo of(final LocalDateTime day, final int sugar){
        return new DrinkDailySugarInfo(day, sugar);
    }
}
