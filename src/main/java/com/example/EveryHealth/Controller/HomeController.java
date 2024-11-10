package com.example.EveryHealth.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String index() {
        String response = "Hello";
        log.info(response);
        return response;
    }
}
