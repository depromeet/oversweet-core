package com.depromeet.oversweet.exception.member;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class DuplicateNicknameException extends OverSweetException {

    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
