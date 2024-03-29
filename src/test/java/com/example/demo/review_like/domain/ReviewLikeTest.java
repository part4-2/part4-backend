package com.example.demo.review_like.domain;

import com.example.demo.review_like.domain.vo.ReviewLikeId;
import org.junit.jupiter.api.Test;
import static com.example.demo.common.test_instance.ReviewLikeFixture.REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;
import static org.assertj.core.api.Assertions.assertThat;
class ReviewLikeTest {
    private static final Long TEST_userId = 1L;
    private static final Long TEST_reviewLikeId = 1L;
    private static final ReviewLikeId REVIEW_LIKE = REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;
    @Test
    void equalsTest(){
        Long userId = REVIEW_LIKE.getUserId();
        Long reviewLikeId = REVIEW_LIKE.getReviewId();

        assertThat(userId).isEqualTo(TEST_userId);
        assertThat(reviewLikeId).isEqualTo(TEST_reviewLikeId);
    }
}