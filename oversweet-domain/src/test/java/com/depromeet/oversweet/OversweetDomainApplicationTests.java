package com.depromeet.oversweet;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 멀티 모듈 프로젝트에서 API 모듈을 제외 한
 * 일반적으로 다른 모듈(예: 도메인 모듈)은 독립형 애플리케이션이 아니며
 * 자체 @SpringBootApplication 주석이 필요하지 않다 판단했습니다.
 * 하지만, Domain 모듈에서 @DataJpaTest를 사용하기 위해서는
 * @SpringBootApplication 주석이 필요하다 판단했습니다.
 * 따라서 Test Package에서만 사용하기 위해, 제일 상위 위치에 @SpringBootApplication 을 추가했습니다.
 */
@SpringBootApplication
public
class OversweetDomainApplicationTests {

    public void contextLoads() {}

}
