package com.depromeet.oversweet;


import com.depromeet.oversweet.domain.franchise.service.FranchisePureService;
import com.depromeet.oversweet.response.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.depromeet.oversweet.exception.ErrorCode.TEST_EXCEPTION;

@Tag(name = "Test", description = "테스트 API")
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final FranchisePureService franchisePureService;


    @GetMapping("/health")
    public String health() {
        return "health";
    }

    @GetMapping("/db-connection")
    public String testDbConnection() {
        return franchisePureService.getFranchiseName(1L);
    }

    @GetMapping("/querydsl")
    public List<String> testQuerydsl() {
        return franchisePureService.getFranchiseNames(List.of(1L, 2L));
    }

    /**
     * 1. OverSweet 을 상속 받은 TestException 검증
     * 2. 정상 데이터 검증
     */
    @GetMapping("/exception-handler/{id}")
    public ResponseEntity<DataResponse<TestDataResponseDto>> testExceptionHandler(@PathVariable("id") Long id) {
        if (id == 1) {
            throw new TestException(TEST_EXCEPTION);
        }
        TestDataResponseDto testDataResponseDto = new TestDataResponseDto("test");
        return new ResponseEntity<>(DataResponse.of(HttpStatus.ACCEPTED, "응답 성공", testDataResponseDto), HttpStatus.ACCEPTED);
    }

    /**
     * Dto로 바인딩 되는 Validation 검증 (NotNull, NotBlank)
     */
    @GetMapping("/exception-handler2")
    public ResponseEntity<DataResponse<TestDataResponseDto>> testExceptionHandler2(@RequestBody @Validated TestDataRequestDto testDataRequestDto) {
        TestDataResponseDto testDataResponseDto = new TestDataResponseDto(testDataRequestDto.getName());
        return new ResponseEntity<>(DataResponse.of(HttpStatus.ACCEPTED, "응답 성공", testDataResponseDto), HttpStatus.ACCEPTED);
    }

}
