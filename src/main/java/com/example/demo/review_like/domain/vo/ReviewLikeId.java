package com.example.demo.review_like.domain.vo;

import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.user.domain.entity.vo.UserId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewLikeId implements Serializable {
    private Long userId;
    private Long reviewId;

    public ReviewLikeId(UserId userId, ReviewId reviewId) {
        this.userId = userId.value();
        this.reviewId = reviewId.value();
    }
}
