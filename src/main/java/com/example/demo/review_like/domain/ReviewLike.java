package com.example.demo.review_like.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike extends BaseTimeEntity {
    @EmbeddedId
    private ReviewLikeId reviewLikeId;
    public ReviewLike(UserId userId, ReviewId reviewId) {
        this.reviewLikeId = new ReviewLikeId(userId, reviewId);
    }
}