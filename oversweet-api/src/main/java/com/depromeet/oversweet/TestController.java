package com.depromeet.oversweet;


import com.depromeet.oversweet.domain.franchise.service.FranchisePureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Test", description = "테스트 API")
@RestController("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final FranchisePureService franchisePureService;


    @GetMapping("/test")
    public String test() {
        TestException testException = new TestException("testException");
        TestDomain testDomain = new TestDomain("testDomain");
        return testException.getMessage() + testDomain.getName();
    }

    @GetMapping("/health")
    public String health(){
        return "health";
    }

    @GetMapping("test-db-connection")
    public String testDbConnection() {
        return franchisePureService.getFranchiseName(1L);
    }

    @GetMapping("test-querydsl")
    public List<String> testQuerydsl() {
        return franchisePureService.getFranchiseNames(List.of(1L, 2L));
    }

}
