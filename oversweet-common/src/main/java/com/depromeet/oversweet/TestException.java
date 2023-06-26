package com.depromeet.oversweet;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class TestException extends OverSweetException {

    public TestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
