package com.depromeet.oversweet.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    private Components securitySetting() {
        return new Components()
                .addSecuritySchemes("accessToken",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("Authorization")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"));
    }

}