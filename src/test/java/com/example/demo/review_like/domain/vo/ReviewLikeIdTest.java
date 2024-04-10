package com.example.demo.review_like.domain.vo;


import com.example.demo.common.test_instance.ReviewLikeFixture;
import com.example.demo.user.domain.entity.vo.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.demo.common.test_instance.ReviewLikeFixture.REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;
import static org.assertj.core.api.Assertions.assertThat;

class ReviewLikeIdTest {

    @Test
    void getValue(){
        final ReviewLikeId reviewLikeId = REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO;
        assertThat(reviewLikeId.getUserId()).isEqualTo(1L);
        assertThat(reviewLikeId.getReviewId()).isEqualTo(1L);
    }
}