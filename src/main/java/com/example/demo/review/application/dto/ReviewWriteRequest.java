package com.example.demo.review.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ReviewWriteRequest(
        @NotBlank(message = "제목은 빈 값일 수 없습니다.")
        String title,
        @NotBlank(message = "내용은 빈 값일 수 없습니다.")
        String content,
        TagValues tagValues,
        LocalDateTime visitingTime
) {


}
