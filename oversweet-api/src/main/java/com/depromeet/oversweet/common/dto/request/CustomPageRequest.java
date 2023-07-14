package com.depromeet.oversweet.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
public class CustomPageRequest {

    @Schema(description = "요청 페이지", example = "0", defaultValue = "0")
    private Integer page;

    @Schema(description = "요청 데이터 수", example = "20", defaultValue = "20")
    private Integer size;

    @Schema(description = "정렬 기준", example = "sugar", defaultValue = "sugar")
    private String order;

    @Schema(description = "정렬 방법", example = "DESC or ASC", defaultValue = "DESC")
    private String direction;

    public CustomPageRequest(Integer page, Integer size, String order, String direction) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.direction = direction;
    }

    public PageRequest of() {
        return PageRequest.of(page, size, Sort.by(Direction.valueOf(direction), order));
    }

}
