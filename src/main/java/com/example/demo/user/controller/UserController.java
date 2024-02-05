package com.example.demo.user.controller;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/test")
    public String test(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return "hello";
    }
}
