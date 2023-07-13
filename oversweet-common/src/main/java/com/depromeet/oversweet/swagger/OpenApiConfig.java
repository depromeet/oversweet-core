package com.depromeet.oversweet.swagger;


import com.depromeet.oversweet.annotation.SecurityExclusion;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        Server serverLocal = new Server()
                .url("http://localhost:8080")
                .description("Local server");
        Server serverProd = new Server()
                .url("https://oversweet-core.shop")
                .description("Production server");

        Info info = new Info()
                .title("OverSweet Core Server API")
                .description("OverSweet Core API 명세서입니다.")
                .version("v0.0.1");


        return new OpenAPI()
                .servers(List.of(serverLocal, serverProd))
                .components(securitySetting())
                .info(info);
    }

    /**
     * Swagger Security 적용
     * - Bearer Authentication 설정
     * - 토큰 검증이 필요한 엔드포인트에 @SecurityRequirement(name = "accessToken") 적용하여 사용 가능
     * - key 값인 accessToken 은 자유롭게 설정 가능
     */
    private Components securitySetting() {
        return new Components()
                .addSecuritySchemes("accessToken",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"));
    }

    /**
     * Swagger Operation 커스텀
     * - @SecurityExclusion 이 붙은 곳은 security 적용 제외
     */
    @Bean
    public OperationCustomizer operationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            SecurityExclusion methodAnnotation = handlerMethod.getMethodAnnotation(SecurityExclusion.class);
            if (Objects.nonNull(methodAnnotation)) {
                operation.setSecurity(Collections.emptyList());
            }
            return operation;
        };
    }

}