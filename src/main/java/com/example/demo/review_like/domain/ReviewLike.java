package com.example.demo.review_like.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ReviewLikeId.class)
public class ReviewLike extends BaseTimeEntity {
    @Id
    private Long reviewId;
    @Id
    private Long userId;
    public ReviewLike(UserId userId, Long reviewId) {
        this.reviewId = reviewId;
        this.userId = userId.value();
    }
}