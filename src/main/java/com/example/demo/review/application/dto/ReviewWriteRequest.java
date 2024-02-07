package com.example.demo.review.application.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewWriteRequest(
        @NotBlank(message = "제목은 빈 값일 수 없습니다.")
        String title,
        @NotBlank(message = "내용은 빈 값일 수 없습니다.")
        String content,
        @NotBlank(message = "날씨는 빈 값일 수 없습니다.")
        String weatherDescription
) {

}