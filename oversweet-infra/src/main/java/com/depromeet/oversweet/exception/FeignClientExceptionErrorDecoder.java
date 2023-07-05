package com.depromeet.oversweet.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

import static com.depromeet.oversweet.exception.ErrorCode.FEIGN_CLIENT_ERROR;

public class FeignClientExceptionErrorDecoder extends OverSweetException implements ErrorDecoder {

    public FeignClientExceptionErrorDecoder(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        return new FeignClientExceptionErrorDecoder(FEIGN_CLIENT_ERROR);
    }
}
