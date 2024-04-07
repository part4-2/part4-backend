package com.example.demo.review_like.domain;

import com.example.demo.review_like.domain.vo.ReviewLikeId;
import org.junit.jupiter.api.Test;

import static com.example.demo.common.test_instance.ReviewLikeFixture.REVIEW_LIKE_BY_DK;
import static com.example.demo.common.test_instance.ReviewLikeFixture.REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewLikeTest {
    private static final ReviewLikeId REVIEW_LIKE = REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;

    @Test
    void equalsTest(){
        Long userId = REVIEW_LIKE.getUserId();
        Long reviewLikeId = REVIEW_LIKE.getReviewId();

        assertThat(userId).isEqualTo(1L);
        assertThat(reviewLikeId).isEqualTo(1L);
    }
}