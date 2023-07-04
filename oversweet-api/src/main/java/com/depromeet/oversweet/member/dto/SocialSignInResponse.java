package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SocialSignInResponse(

        @Schema(description = "회원 고유 ID", example = "1") Long id,

        @Schema(description = "필수 정보 기입 여부", example = "true") boolean isRequiredInfoExist

) {
    public SocialSignInResponse(final Long id, final boolean isRequiredInfoExist) {
        this.id = id;
        this.isRequiredInfoExist = isRequiredInfoExist;
    }
}
