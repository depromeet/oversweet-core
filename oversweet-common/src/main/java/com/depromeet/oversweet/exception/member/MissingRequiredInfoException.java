package com.depromeet.oversweet.exception.member;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class MissingRequiredInfoException extends OverSweetException {

    public MissingRequiredInfoException(ErrorCode errorCode) {
        super(errorCode);
    }

}
