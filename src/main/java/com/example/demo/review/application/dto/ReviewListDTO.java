package com.example.demo.review.application.dto;

public record ReviewListDTO(Long reviewId,
                            String title,
                            TagValues tagValues,
                            String nickName,
                            String visitingTime,
                            Double stars,
                            String image) {
}
