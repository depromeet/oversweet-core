package com.depromeet.oversweet.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 멤버 정보 dto
 */
public record MemberInfo(
        @Schema(description = "유저 고유 Id") Long userId,
        @Schema(description = "유저 이름") String nickname,
        @Schema(description = "유저의 적정 당 섭취량") int dailySugar
) {
    public static MemberInfo of(final Long userId, final String nickname, final int dailySugar){
        return new MemberInfo(userId, nickname, dailySugar);
    }
}
