package com.example.demo.oauth.controller;

import com.example.demo.oauth.LoginResponse;
import com.example.demo.oauth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponse loginResponse = oAuth2Service.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
