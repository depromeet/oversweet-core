package com.depromeet.oversweet.exception.franchise;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

/**
 * 프랜차이즈를 찾을 수 없을 때 발생하는 예외
 */
public class NotFoundFranchiseException extends OverSweetException {
    public NotFoundFranchiseException(ErrorCode errorCode) {
        super(errorCode);
    }
}
