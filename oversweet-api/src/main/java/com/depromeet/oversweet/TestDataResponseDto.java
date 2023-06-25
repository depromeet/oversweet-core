package com.depromeet.oversweet;

import lombok.Getter;

@Getter
public class TestDataResponseDto {

    private final String name;

    public TestDataResponseDto(String name) {
        this.name = name;
    }
}
