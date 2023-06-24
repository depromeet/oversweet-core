package com.depromeet.oversweet.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        Info info = new Info()
                .title("OverSweet Core Server API")
                .description("OverSweet Core API 명세서입니다.")
                .version("v0.0.1");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}