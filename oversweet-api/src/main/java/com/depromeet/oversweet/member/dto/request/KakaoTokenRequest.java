package com.depromeet.oversweet.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record KakaoTokenRequest(@Schema(description = "access token") String accessToken) {
}