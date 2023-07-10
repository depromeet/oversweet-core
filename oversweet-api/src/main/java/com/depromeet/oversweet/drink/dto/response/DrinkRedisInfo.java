package com.depromeet.oversweet.drink.dto.response;

import com.depromeet.oversweet.domain.drink.dto.DrinkSimpleInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 레디스에 저장될 음료 간편 정보 DTO
 */
public record DrinkRedisInfo(
        @Schema(description = "음료 ID", example = "1") Long id,

        @Schema(description = "음료 이름", example = "아메리카노") String name
) {
    public static DrinkRedisInfo of(final DrinkSimpleInfo drinkSimpleInfo){
        return new DrinkRedisInfo(drinkSimpleInfo.getId(), drinkSimpleInfo.getName());
    }
}
