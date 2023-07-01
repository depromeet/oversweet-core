package com.depromeet.oversweet.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignInResponse(
        //닉네임 등 데이터 더 넘겨줄기 고민
        @Schema(description = "닉네임") String nickname,

        @Schema(description = "신규 회원 여부") boolean isExist
) {
}
