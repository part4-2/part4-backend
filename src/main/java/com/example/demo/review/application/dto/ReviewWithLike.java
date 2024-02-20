package com.example.demo.review.application.dto;


import com.example.demo.review.domain.Review;
import com.example.demo.review_photo.domain.ReviewPhoto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewWithLike(Long reviewId,
                             String placeId, //
                             String title, //
                             String content, //
                             TagValues tagValues,
                             String nickName,
                             String spotName,
                             LocalDateTime createdAt,
                             LocalDateTime modifiedAt,
                             int likeCount,
                             LocalDateTime visitingTime,
                             Double stars,
                             List<String> images
                             ) {
    public static ReviewWithLike of(final Review review, final int reviewCount) {
        return new ReviewWithLike(
                review.getId(),
                review.getSpot().getPlaceId(),
                review.getTitle().getValue(),
                review.getContent().getValue(),
                TagValues.of(review.getTag()),
                review.getUsers().getNickName(), // TODO : 여기 처리해야 함 (페치조인하게)
                review.getSpot().getDisplayName(),
                review.getCreatedDate(),
                review.getModifiedDate(),
                reviewCount,
                review.getVisitingTime(),
                review.getStarRank().getValue(),
                review.getReviewPhotos().stream().map(ReviewPhoto::getUrl).toList()
        );
    }

}
