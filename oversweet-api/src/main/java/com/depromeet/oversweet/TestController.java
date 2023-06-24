package com.depromeet.oversweet;


import com.depromeet.oversweet.domain.franchise.service.FranchisePureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

}
