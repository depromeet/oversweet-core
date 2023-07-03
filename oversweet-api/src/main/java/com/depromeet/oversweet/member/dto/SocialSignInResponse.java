package com.depromeet.oversweet.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SocialSignInResponse(

        @Schema(description = "회원 고유 ID", example = "1") Long id,

        @Schema(description = "신규 회원 여부", example = "true") boolean isNewMember

) {
    public SocialSignInResponse(final Long id, final boolean isNewMember) {
        this.id = id;
        this.isNewMember = isNewMember;
    }
}
