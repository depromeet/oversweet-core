package com.depromeet.oversweet.exception.redis;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class OverSweetRedisException extends OverSweetException {
    public OverSweetRedisException(ErrorCode errorCode) {
        super(errorCode);
    }
}
