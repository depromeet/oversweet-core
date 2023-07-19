package com.depromeet.oversweet.exception.record;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class NotFoundRecordException extends OverSweetException {
    public NotFoundRecordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
