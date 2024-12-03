package com.example.EveryHealth.service;

import com.example.EveryHealth.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtUtil jwtUtil;

    public String generateAccessToken(String email) {
        return jwtUtil.generateAccessToken(email);
    }

    public String generateRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }

    public boolean validateAccessToken(HttpServletRequest request) {
        String token = extractAccessToken(request);
        if(token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        String jwtToken = token.substring(7);
        return !(jwtUtil.isTokenExpired(jwtToken));
    }

    private String extractAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header;
        }
        return null;
    }

    public String refreshAccessToken(HttpServletRequest request) {
        String refreshToken = extractRefreshToken(request);

        if(refreshToken == null) {
            throw new IllegalArgumentException("refresh token이 존재하지 않습니다.");
        }

        String email = jwtUtil.extractEmail(refreshToken);
        if(!jwtUtil.validateRefreshToken(refreshToken, email)) {
            throw new IllegalArgumentException("refresh token이 유효하지 않습니다.");
        }

        return jwtUtil.generateAccessToken(email);
    }

    private String extractRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
