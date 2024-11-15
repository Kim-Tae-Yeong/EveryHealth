package com.example.EveryHealth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").permitAll()  // 특정 경로 허용
                        .anyRequest().authenticated()            // 나머지 경로 인증 필요
                )
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("http://localhost:8082"); // Vue 서버 주소
                    corsConfiguration.addAllowedMethod("*");  // 모든 HTTP 메서드 허용
                    corsConfiguration.addAllowedHeader("*");  // 모든 헤더 허용
                    return corsConfiguration;
                }));
        return http.build();
    }
}
