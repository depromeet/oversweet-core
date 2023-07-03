package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignUpResponse(

        @Schema(description = "하루 권장 당 섭취량", example = "25") Integer dailySugar
) {
}