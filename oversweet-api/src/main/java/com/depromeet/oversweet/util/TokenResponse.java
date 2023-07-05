package com.depromeet.oversweet.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TokenResponse(
        @Schema(description = "accessToken") String accessToken,

        @Schema(description = "refreshToken") String refreshToken
) {
}