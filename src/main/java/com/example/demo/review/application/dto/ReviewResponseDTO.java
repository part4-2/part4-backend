package com.example.demo.review.application.dto;

import com.example.demo.review.domain.Review;


import java.time.LocalDateTime;

public record ReviewResponseDTO(Long reviewId,
                                String title,
                                String content,
                                TagValues tagValues,
                                Long userId,
                                String spotId,
                                LocalDateTime createdAt,
                                LocalDateTime modifiedAt) {
    public static ReviewResponseDTO of(final Review review){
        return new ReviewResponseDTO(
                review.getId(),
                review.getTitle().getValue(),
                review.getContent().getValue(),
                TagValues.of(review.getTag()),
                review.getUsers().getId(),
                review.getSpot().getPlaceId(),
                review.getCreatedDate(),
                review.getModifiedDate()
        );
    }
}