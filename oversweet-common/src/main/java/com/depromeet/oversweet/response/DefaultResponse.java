package com.depromeet.oversweet.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 공통된 응답 형식을 제공하기 위한 응답 DTO .
 */
@Getter
public abstract class DefaultResponse {

    @Schema(description = "응답 코드")
    protected int status;
    @Schema(description = "응답 메시지")
    protected String message;

    protected DefaultResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
