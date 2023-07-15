package com.depromeet.oversweet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://oversweet.vercel.app")
                // Set the list of headers that a pre-flight request can list as allowed for use during an actual request.
                .allowedHeaders("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.OPTIONS.name()
                )
                // allow to use cookies (쿠키 사용을 허용할지 여부) : cookie에 저장된 refresh token을 사용하기 위해 필요
                .allowCredentials(true)
                // allow to read response Authorization header
                .exposedHeaders("Authorization", "Set-Cookie");

    }
}
