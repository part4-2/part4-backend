package com.example.demo.review_like.domain.vo;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ReviewLikeIdTest {
    private static final Long TEST_userId = 1L;
    private static final Long TEST_reviewLikeId = 1L;

    @Test
    void getValue(){
        final ReviewLikeId reviewLikeId = new ReviewLikeId();
        assertThat(reviewLikeId.getUserId()).isEqualTo(TEST_userId);
        assertThat(reviewLikeId.getReviewId()).isEqualTo(TEST_reviewLikeId);
    }
}