package com.example.demo.oauth.controller;

import com.example.demo.oauth.LoginResponse;
import com.example.demo.oauth.service.OAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@Tag(name = "OAuth2Controller" , description = "소셜 로그인 컨트롤러")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @Operation(summary = "소셜로그인",description = "소셜 로그인을 통한 회원가입")
    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code) {

        if (provider.equals("google")) {
            code = URLDecoder.decode(code, StandardCharsets.UTF_8);
        }
        LoginResponse loginResponse = oAuth2Service.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
