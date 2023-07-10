package com.depromeet.oversweet.exception.security;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class ExpiredTokenException extends OverSweetException {

    public ExpiredTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
