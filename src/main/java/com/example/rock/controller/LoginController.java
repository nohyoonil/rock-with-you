package com.example.rock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Value("${kakao.auth.client}")
    private String client;
    @Value("${kakao.auth.redirect}")
    private String redirect;

    @GetMapping("/auth/login/kakao")
    public String kakaoLogin(@RequestParam("code") String accessCode) {
        return "kakao";
    }

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping("/api/test")
    public String test() {
        return "api test";
    }
}
