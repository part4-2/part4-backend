package com.example.demo.review.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record ReviewWriteRequest(
        @NotBlank(message = "제목은 빈 값일 수 없습니다.")
        String title,
        @NotBlank(message = "내용은 빈 값일 수 없습니다.")
        String content,
        TagValues tagValues,
        String visitingTime,
        Double starRank,
        List<MultipartFile> images
) {
        @Override
        public List<MultipartFile> images() {
                if (Objects.isNull(images)) {
                        return Collections.emptyList();
                }

                return images;
        }
}