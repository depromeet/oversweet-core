package com.depromeet.oversweet.exception.drink;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class NotFoundDrinkException extends OverSweetException {
    public NotFoundDrinkException(ErrorCode errorCode) {
        super(errorCode);
    }
}
