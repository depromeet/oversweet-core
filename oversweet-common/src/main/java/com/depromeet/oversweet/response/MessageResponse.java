package com.depromeet.oversweet.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 일반적인 메시지와 코드 번호만 반환하는 경우
 */
@Getter
public class MessageResponse extends DefaultResponse {

    private MessageResponse(HttpStatus status, String message) {
        super(status.value(), message);
    }

    public static MessageResponse of(HttpStatus status, String message) {
        return new MessageResponse(status, message);
    }
}
