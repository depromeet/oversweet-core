package com.depromeet.oversweet.exception.member;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class NotFoundMemberException extends OverSweetException {

    public NotFoundMemberException(ErrorCode errorCode) {
        super(errorCode);
    }

}
