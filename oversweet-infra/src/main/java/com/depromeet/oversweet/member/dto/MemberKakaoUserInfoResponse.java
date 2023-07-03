package com.depromeet.oversweet.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberKakaoUserInfoResponse(

        @Schema(description = "사용자 ID", example = "1234") String id,

        @JsonProperty(value = "connected_at")
        @Schema(description = "서비스에 연결 완료된 시각", example = "") String connectedAt,

        @Schema(description = "추가 정보") Map<String, Object> properties,

        @JsonProperty(value = "kakao_account")
        @Schema(description = "카카오계정 정보") Map<String, Object> kakaoAccount
) {
}
