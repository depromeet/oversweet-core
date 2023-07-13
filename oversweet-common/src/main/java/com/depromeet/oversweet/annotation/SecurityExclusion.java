package com.depromeet.oversweet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Swagger Security 적용에서 제외시키는 어노테이션
 * - 토큰 검증이 불필요한 엔드포인트에 @SecurityExclusion 적용하여 사용 가능
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityExclusion {
}
