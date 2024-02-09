package com.example.demo.common.test_instance;

import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.domain.vo.Weather;

public class ReviewFixture {
    private static final String TEST = "TEST";
    private static final String FOO = "FOO";

    public static final Review REVIEW_OF_ALL_FIELD_VALUE_IS_FOO = new Review(
            new Title(FOO),
            new Content(FOO),
            Weather.NONE,
            UserFixture.FOO,
            SpotFixture.FOO_SPOT
    );
    public static final Review REVIEW_ON_SPOT_1_BY_DK = new Review(
            new Title(TEST),
            new Content(TEST),
            Weather.SNOWY,
            UserFixture.DK_USER,
            SpotFixture.SPOT
    );

    public static final Review REVIEW_ON_SPOT_1_BY_DK_ADMIN = new Review(
            new Title(TEST),
            new Content(TEST),
            Weather.SNOWY,
            UserFixture.DK_ADMIN,
            SpotFixture.SPOT
    );
}
