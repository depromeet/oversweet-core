package com.depromeet.oversweet.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Error Response 의 of 메서드를 통해서 ErrorResponse 를 생성한다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    /**
     * 메인 Error 메시지
     */
    private String message;

    /**
     * Http 상태 코드
     */
    private int status;

    /**
     * 주로 유효성 검사, Controller 에서 받지 못한 필드에 대한 에러가 담긴다.
     */
    private List<FieldError> errors;

    /**
     * 우리 시스템에서 정의한 Custom Error Code
     */
    private String code;


    /**
     * Valid 를 사용할 경우, 여러 파라미터에 대해서 유효성 검사를 할 수 있다.
     *
     * @param code   ErrorCode Enum 클래스를 넣는다.
     * @param errors @Valid 에서 유효성 검사를 통과하지 못한 필드
     */
    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    /**
     * 일반적인 상황에서 Error 를 반환하는 경우
     * errors 필드에는 빈 배열을 반환한다.
     *
     * @param code ErrorCode Enum 클래스를 넣는다
     */
    public ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }


    /**
     * Controller 단에서 파라미터에 대한 유효성 검사가 실패하는 경우 사용된다.
     * 주로 @Valid, @Validated 어노테이션을 사용할 경우에 사용된다.
     *
     * @param code          ErrorCode Enum 클래스를 넣는다
     * @param bindingResult 유효성 검사가 실패한 필드값이 담긴다.
     * @return ErrorResponse 를 반환한다.
     */
    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    /**
     * 일반적인 Error 의 경우 사용된다.
     *
     * @param code ErrorCode Enum 클래스를 넣는다
     * @return ErrorResponse 를 반환한다.
     */
    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {

        /**
         * 필드 변수 이름
         */
        private String field;

        /**
         * 잘못된 값
         */
        private String value;

        /**
         * 이유
         */
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        /**
         * Client 로 넘어온 데이터 값이 controller 에서 타입 변환이 불가능할때 사용된다.
         *
         * @param field  변환이 불가한 필드 변수 명
         * @param value  Client 로 넘어온 잘못된 요청 값
         * @param reason 해당 Error 가 발생한 이유
         * @return FieldError List 를 반환한다.
         */
        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }


        /**
         * Controller 단에서 유효성 검사가 실패 했을 경우의 결과를 받아서 처리한다.
         *
         * @param bindingResult 유효성 검사가 실패한 결과가 담겨 있다.
         * @return FieldError List 를 반환한다.
         */

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }


}
