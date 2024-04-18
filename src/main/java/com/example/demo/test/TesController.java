package com.example.demo.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TesController {
    private final TestService testService;
    @PostMapping("/test/tokens")
    public String getToken(@RequestParam String email){
        return testService.login(email);
    }

    @GetMapping("/health/check")
    public String foo(){
        return "foo";
    }
}