package com.depromeet.oversweet.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 365; // 1년
    private static final Long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 365; // 1년

    private static Key secretKey;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String jwtSecretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
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
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().getTime() >= new Date().getTime();
    }

    //토큰에서 Subject 추출(id)
    private String getSubjectFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
