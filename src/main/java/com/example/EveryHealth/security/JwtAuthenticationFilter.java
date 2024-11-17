package com.example.EveryHealth.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 토큰 추출
        String token = extractToken(request);

        if (token != null && jwtUtil.validateToken(token, extractUsername(token))) {
            // 토큰이 유효하다면 UsernamePasswordAuthenticationToken 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    extractUsername(token), null, null);

            // 인증 객체를 SecurityContext에 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 필터 체인 진행
        filterChain.doFilter(request, response);
    }

    // HTTP 요청 헤더에서 JWT 토큰 추출
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }

    // 토큰에서 사용자 이름 추출
    private String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }
}
