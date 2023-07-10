package com.depromeet.oversweet.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TokenResponse(
        @Schema(description = "accessToken") String accessToken,

        @Schema(description = "refreshToken") String refreshToken
) {
}