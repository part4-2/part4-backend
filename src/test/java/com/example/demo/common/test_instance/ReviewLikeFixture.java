package com.example.demo.common.test_instance;

import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;

public class ReviewLikeFixture {
    public static final ReviewLikeId REVIEW_LIKE_OF_ALL_FIELD_VALUE_IS_FOO = new ReviewLikeId(
            new UserId(1L),
            1L
    );

    public static final ReviewLikeId REVIEW_LIKE_ID_BY_DK = new ReviewLikeId(new UserId(1L), 1L);

    public static final ReviewLike REVIEW_LIKE_BY_DK = new ReviewLike(
            new UserId(1L),
            1L
    );

    public static final ReviewLike REVIEW_LIKE_BY_IH = new ReviewLike(
            new UserId(2L),
            2L
    );

    public static final ReviewLike REVIEW_LIKE_BY_SI = new ReviewLike(
            new UserId(3L),
            1L
    );

}