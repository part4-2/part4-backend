package com.example.demo.common.test_instance;

import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.review.domain.vo.Title;

import java.time.LocalDateTime;

public class ReviewFixture {
    private static final String TEST = "TEST";
    private static final String FOO = "FOO";

    public static final Review REVIEW_OF_ALL_FIELD_VALUE_IS_FOO = new Review(
            new Title(FOO),
            new Content(FOO),
            TagFixture.TAG_OF_NONE,
            UserFixture.FOO,
            SpotFixture.FOO_SPOT,
            LocalDateTime.now(),
            StarRank.ZERO
    );
    public static final Review REVIEW_ON_SPOT_1_BY_DK = new Review(
            new Title(TEST),
            new Content(TEST),
            TagFixture.TAG_OF_NONE,
            UserFixture.DK_USER,
            SpotFixture.SPOT,
            LocalDateTime.now(),
            StarRank.ZERO
    );

    public static final Review REVIEW_ON_SPOT_1_BY_DK_ADMIN = new Review(
            new Title(TEST),
            new Content(TEST),
            TagFixture.TAG_OF_NONE,
            UserFixture.DK_ADMIN,
            SpotFixture.SPOT,
            LocalDateTime.now(),
            StarRank.ZERO
    );
}
