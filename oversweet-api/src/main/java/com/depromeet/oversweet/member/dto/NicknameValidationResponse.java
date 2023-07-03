package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NicknameValidationResponse(

        @Schema(description = "닉네임 존재 시 해당 닉네임 / 없을 경우 null") String nickname
) {
}
