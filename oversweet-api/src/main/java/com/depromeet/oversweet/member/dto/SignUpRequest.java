package com.depromeet.oversweet.member.dto;

import com.depromeet.oversweet.domain.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SignUpRequest(

        @NotNull
        @Schema(description = "멤버 고유 ID") Long id,

        @NotNull
        @Schema(description = "성별") Gender gender,

        @NotNull
        @Schema(description = "몸무게") Integer weight,

        @NotNull
        @Schema(description = "키") Integer height,

        @NotNull
        @Schema(description = "나이") Integer age,

        @NotBlank
        @Schema(description = "닉네임") String nickname
) {
}