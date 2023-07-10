package com.depromeet.oversweet.security.filter;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.ErrorResponse;
import com.depromeet.oversweet.exception.OverSweetException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (OverSweetException e) {
            writeErrorResponse(response, e.getErrorCode());
        } catch (Exception e) {
            if (e.getCause() instanceof OverSweetException) {
                writeErrorResponse(response, ((OverSweetException) e.getCause()).getErrorCode());
            } else {
                log.error("error : {}", e);
                writeErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
