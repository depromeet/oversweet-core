package com.depromeet.oversweet.drink.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 음료 상세 정보를 보기 위한 요청 DTO
 */
@Getter
public class DrinkInfoRequest {

    @Schema(description = "프랜차이즈 ID", example = "1")
    @NotNull(message = "프랜차이즈 ID는 필수 값입니다.")
    private Long franchiseId;

    @Schema(description = "음료 이름", example = "아메리카노")
    @NotEmpty(message = "음료 이름은 필수 값입니다.")
    private String drinkName;


}
