package com.depromeet.oversweet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
public class TestDataRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "나이를 입력해주세요.")
    private Integer age;

}
