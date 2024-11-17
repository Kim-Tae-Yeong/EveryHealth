package com.example.EveryHealth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig jwtConfig;

    // SecretKey 생성 (HMAC 알고리즘에 사용할 키)
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

    // JWT 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // 사용자 정보
                .setIssuedAt(new Date())  // 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))  // 만료 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // SecretKey를 사용한 서명
                .compact();
    }

    // JWT에서 Claims (사용자 정보 등) 추출
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // SecretKey를 사용하여 서명 검증
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 토큰 검증
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
