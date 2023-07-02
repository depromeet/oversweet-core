package com.depromeet.oversweet.exception.bookmark;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.OverSweetException;

/**
 * 이미 즐겨찾기한 프랜차이즈를 다시 즐겨찾기할 때 발생하는 예외
 */
public class AlreadyFranchiseBookMarked extends OverSweetException {
    public AlreadyFranchiseBookMarked(ErrorCode errorCode) {
        super(errorCode);
    }
}
