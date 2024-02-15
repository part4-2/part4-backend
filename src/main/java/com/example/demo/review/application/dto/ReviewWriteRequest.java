package com.example.demo.review.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ReviewWriteRequest(
        @NotBlank(message = "제목은 빈 값일 수 없습니다.")
        String title,
        @NotBlank(message = "내용은 빈 값일 수 없습니다.")
        String content,
        String visitingTime,
        String weather,
        String companion,
        String placeType,
        Double stars,
        List<MultipartFile> images
) {


}
