package com.depromeet.oversweet.record.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DrinkRecordSaveRequest(

        @NotNull
        @Schema(description = "음료 ID", example = "1") Long id,

        @NotNull
        @Min(1)
        @Schema(description = "마신 잔의 수", example = "2") Integer count,

        @NotNull
        @Min(0)
        @Schema(description = "1잔 기준 최종 섭취 당 함량", example = "10") Integer intakeSugar

) {
}
