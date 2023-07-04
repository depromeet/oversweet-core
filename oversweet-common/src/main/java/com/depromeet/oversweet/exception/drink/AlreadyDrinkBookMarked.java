package com.depromeet.oversweet.exception.drink;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

public class AlreadyDrinkBookMarked extends OverSweetException {
    public AlreadyDrinkBookMarked(ErrorCode errorCode) {
        super(errorCode);
    }
}
