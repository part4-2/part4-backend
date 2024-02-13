package com.example.demo.review.application.dto;


import com.example.demo.review.domain.Review;

import java.time.LocalDateTime;

public record ReviewWithLike(Long reviewId,
                             String title,
                             String content,
                             TagValues tagValues,
                             Long userId,
                             String spotId,
                             LocalDateTime createdAt,
                             LocalDateTime modifiedAt,
                             int likeCount,
                             LocalDateTime visitingTime) {
    public static ReviewWithLike of(final Review review, final int reviewCount) {
        return new ReviewWithLike(
                review.getId(),
                review.getTitle().getValue(),
                review.getContent().getValue(),
                TagValues.of(review.getTag()),
                review.getUsers().getId(),
                review.getSpot().getPlaceId(),
                review.getCreatedDate(),
                review.getModifiedDate(),
                reviewCount,
                review.getVisitingTime()
        );
    }

}
