package com.depromeet.oversweet.config;

import com.depromeet.oversweet.exception.FeignClientExceptionErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

import static com.depromeet.oversweet.exception.ErrorCode.FEIGN_CLIENT_ERROR;

public class FeignConfig {

    //400 에러
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientExceptionErrorDecoder(FEIGN_CLIENT_ERROR);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
