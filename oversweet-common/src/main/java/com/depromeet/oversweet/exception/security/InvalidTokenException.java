package com.depromeet.oversweet.exception.security;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class InvalidTokenException extends OverSweetException {

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
