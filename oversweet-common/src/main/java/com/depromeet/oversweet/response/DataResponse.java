package com.depromeet.oversweet.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 상태 코드 숫자랑 메시지, 데이터를 함께 반환하는 경우
 *
 * @param <T>
 */
@Getter
public class DataResponse<T> extends DefaultResponse {

    @Schema(description = "응답 데이터")
    private final T data;

    private DataResponse(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

    public static <T> DataResponse<T> of(HttpStatus status, String message, T data) {
        return new DataResponse<>(status, message, data);
    }
}
