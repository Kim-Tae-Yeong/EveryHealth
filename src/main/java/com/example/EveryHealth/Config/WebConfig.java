package com.example.EveryHealth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // CORS를 허용할 API 경로
                        .allowedOrigins("http://localhost:8082")  // Vue 서버 주소
                        .allowedMethods("GET", "POST", "PUT", "DELETE");  // 허용할 HTTP 메서드
            }
        };
    }
}

