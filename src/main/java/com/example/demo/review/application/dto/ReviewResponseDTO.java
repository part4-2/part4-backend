package com.example.demo.review.application.dto;

import com.example.demo.review.domain.Review;

public record ReviewResponseDTO(String title,
                                String content,
                                String weather,
                                Long userId,
                                Long spotId) {
    public static ReviewResponseDTO of(final Review review){
        return new ReviewResponseDTO(
                review.getTitle().getValue(),
                review.getContent().getValue(),
                review.getWeather().getCode(),
                review.getUsers().getId(),
                review.getSpot().getId()
        );
    }
}
