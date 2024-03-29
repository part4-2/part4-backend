package com.example.demo.common.test_instance;

import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;

public class ReviewLikeFixture {
    private final static Long TEST_USERID = 1L;
    private final static Long TEST_REVIEW_ID = 1L;

    public static final ReviewLikeId REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO = new ReviewLikeId(
            new UserId(TEST_USERID),
            TEST_REVIEW_ID
    );
}
