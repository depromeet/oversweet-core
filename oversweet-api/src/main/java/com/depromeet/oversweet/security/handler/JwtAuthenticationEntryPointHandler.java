package com.depromeet.oversweet.security.handler;

import com.depromeet.oversweet.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.depromeet.oversweet.exception.ErrorCode.ACCESS_DENIED;

/**
 * 유효한 자격증명을 제공하지 않고 접근하려 할 때, 403 Forbidden 에러를 리턴하는 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(ACCESS_DENIED);
        response.setStatus(ACCESS_DENIED.getStatus());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
