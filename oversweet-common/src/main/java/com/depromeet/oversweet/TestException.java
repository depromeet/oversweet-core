package com.depromeet.oversweet;

public class TestException extends RuntimeException {

    private final String message;

    public TestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
