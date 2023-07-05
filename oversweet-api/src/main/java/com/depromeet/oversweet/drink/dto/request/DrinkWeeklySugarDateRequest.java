package com.depromeet.oversweet.drink.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 주간 날짜 정보 Request
 */
public record DrinkWeeklySugarDateRequest(
        @Schema(description = "조회를 시작할 시작 날짜")
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @Schema(description = "조회를 끝낼 마지막 날짜")
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate
){

}
