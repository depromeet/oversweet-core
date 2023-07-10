package com.depromeet.oversweet.security.jwt;

import com.depromeet.oversweet.exception.security.ExpiredTokenException;
import com.depromeet.oversweet.exception.security.InvalidTokenException;
import com.depromeet.oversweet.security.service.CustomUserDetails;
import com.depromeet.oversweet.security.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.regex.Pattern;

import static com.depromeet.oversweet.exception.ErrorCode.EXPIRED_TOKEN_ERROR;
import static com.depromeet.oversweet.exception.ErrorCode.INVALID_TOKEN_ERROR;

@Component
public class JwtTokenProvider {

    private static final Long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 365; // 1년
    private static final Long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 365; // 1년

    private static Key secretKey;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String jwtSecretKey,
                            CustomUserDetailsService customUserDetailsService) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
        this.customUserDetailsService = customUserDetailsService;
    }

    //토큰 생성
    public TokenResponse generateJwtToken(Long id) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    //토큰 유효성 검사
    public Boolean isTokenValid(String token) {
        Claims claims = getJws(token).getBody();
        return claims.getExpiration().getTime() >= new Date().getTime();
    }

    //토큰에서 Subject 추출(id)
    private String getSubjectFromToken(String token) {
        return getJws(token).getBody().getSubject();
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(EXPIRED_TOKEN_ERROR);
        } catch (Exception e) {
            throw new InvalidTokenException(INVALID_TOKEN_ERROR);
        }
    }

    public String resolveTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && Pattern.matches("^Bearer .*", authorization)) {
            return authorization.replaceAll("^Bearer( )*", "");
        }

        return null;
    }

    public Authentication getAuthentication(String accessToken) {
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(getSubjectFromToken(accessToken));
        return new UsernamePasswordAuthenticationToken(customUserDetails, "", customUserDetails.getAuthorities());
    }
}
