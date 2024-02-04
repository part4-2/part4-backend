package com.example.demo.oauth.controller;

import com.example.demo.oauth.LoginResponse;
import com.example.demo.oauth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponse loginResponse = oAuth2Service.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }


}
