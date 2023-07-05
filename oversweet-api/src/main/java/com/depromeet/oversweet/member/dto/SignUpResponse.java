package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignUpResponse(

        @Schema(description = "멤버 고유 ID", example = "1") Long id,

        @Schema(description = "하루 권장 당 섭취량", example = "25") Integer dailySugar
) {
}