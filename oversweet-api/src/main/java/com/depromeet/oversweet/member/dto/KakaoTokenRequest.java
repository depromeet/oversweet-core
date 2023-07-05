package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record KakaoTokenRequest(@Schema(description = "accessToken") String accessToken) {
}