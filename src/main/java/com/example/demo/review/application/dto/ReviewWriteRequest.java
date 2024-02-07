package com.example.demo.review.application.dto;

public record ReviewWriteRequest(
        String title,
        String content,
        String weatherDescription
) {

}
