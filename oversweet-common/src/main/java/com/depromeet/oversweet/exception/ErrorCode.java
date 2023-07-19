package com.depromeet.oversweet.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode 를 정의하는 Enum Class 이다.
 * ErrorCode 는 HttpStatus, ErrorCode, ErrorMessage 를 가진다.
 */
@Getter
public enum ErrorCode {

    // Common (공통적으로 사용)
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "값이 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "지원하지 않는 Http Method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 에러"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C004", "입력 값의 타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C005", "접근이 거부 되었습니다."),
    FEIGN_CLIENT_ERROR(HttpStatus.BAD_REQUEST, "C006", "정보를 가져올 수 없습니다."),

    // Member (유저)
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "M001", "존재 하지 않는 유저 입니다."),
    NICKNAME_DUPLICATED_ERROR(HttpStatus.BAD_REQUEST, "M002", "중복된 닉네임 입니다."),

    // BookMark (즐겨찾기)
    ALREADY_FRANCHISE_BOOK_MARKED(HttpStatus.BAD_REQUEST, "B001", "이미 즐겨찾기한 프랜차이즈 입니다."),
    ALREADY_DRINK_BOOK_MARKED(HttpStatus.BAD_REQUEST, "B002", "이미 즐겨찾기한 음료 입니다."),

    // Franchise (프랜차이즈)
    NOT_FOUND_FRANCHISE(HttpStatus.NOT_FOUND, "F001", "존재 하지 않는 프랜차이즈 입니다."),

    // Drink (음료)
    NOT_FOUND_DRINK(HttpStatus.NOT_FOUND, "D001", "존재 하지 않는 음료 입니다."),

    // Redis (레디스)
    OVERSWEET_REDIS_JSON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "R001", "레디스 직렬화/역직렬화 과정에서 오류가 발생했습니다."),

    // Security (jwt 토큰 및 Security)
    EXPIRED_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "S001", "토큰이 만료되었습니다."),
    INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "S002", "토큰이 유효하지 않습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "S003", "토큰이 존재하지 않아 접근이 불가합니다."),

    // Record
    NOT_FOUND_RECORD(HttpStatus.NOT_FOUND, "R001", "존재하지 않는 기록입니다."),

    // Test
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "T001", "테스트 에러");


    // Depend on Entity (도메인에 따라서 달라지는 경우)
    // 이벤트 Entity 관련
    // EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "E001", "이메일 중복 입니다."),
    // EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "E002", "존재하지 않는 이벤트입니다."),
    // EVENT_OVER_DEADLINE(HttpStatus.BAD_REQUEST, "E003", "신청 기간이 지났습니다."),
    // EVENT_TYPE_NOT_MATCH(HttpStatus.BAD_REQUEST, "E004", "모집 유형이 일치하지 않습니다."),
    // EVENT_TYPE_ENUM_NOT_SUPPORTED(HttpStatus.BAD_REQUEST, "E005", "지원하지 않는 이벤트 유형입니다."),


    // JWT (Json Web Token)
    //JWT_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "J001", "유효하지 않은 토큰입니다."),
    //JWT_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "J002", "만료된 토큰입니다."),
    //JWT_REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "J003", "만료된 리프레시 토큰입니다."),
    //ACCESS_TOKEN_LOGOUT(HttpStatus.UNAUTHORIZED, "J004", "로그아웃된 액세스 토큰입니다."),

    // 리소스 요청에 대한 권한이 없는 경우
    //RESOURCE_FORBIDDEN(HttpStatus.FORBIDDEN, "R001", "해당 리소스에 대한 권한이 없습니다."),
    //RESOURCE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "R002", "인증이 필요합니다. 로그인을 해주세요."),

    // 소셜 로그인 서비스에 access token 을 통해 사용자 데이터 요청하는데 실패한 경우
    //SOCIAL_ACCESS_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "S001", "유효하지 않은 소셜 로그인 토큰입니다."),

    // S3를 사용해 이미지를 업로드할 때
    //IMAGE_WRONG_FILE_FORMAT(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "I001", "파일 형식이 잘못되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    public int getStatus() {
        return this.status.value();
    }

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
