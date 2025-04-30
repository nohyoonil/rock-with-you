package com.example.rock.controller;

import com.example.rock.config.annotation.LoginId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping("/api/test")
    public String test() {
        return "api test success!";
    }

    @GetMapping("/api/login-success")
    public String loginSuccess(@LoginId Long userId) {
        return "userId == " + userId;
    }

}
