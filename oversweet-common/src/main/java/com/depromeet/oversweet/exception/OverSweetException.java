package com.depromeet.oversweet.exception;

import lombok.Getter;

/**
 * 사용자 정의 Exception 을 만들기 위해서는
 * 해당 OverSweetException 을 상속 받아야 한다.
 */
@Getter
public abstract class OverSweetException extends RuntimeException {

    private final ErrorCode errorCode;

    protected OverSweetException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
