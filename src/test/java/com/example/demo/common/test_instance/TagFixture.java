package com.example.demo.common.test_instance;

import com.example.demo.review.domain.vo.Companion;
import com.example.demo.review.domain.vo.PlaceType;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Weather;

public class TagFixture {
    public static final Tag TAG_OF_NONE = Tag.ofNone();
    public static final Tag TAG = new Tag(
            Weather.SNOWY,
            Companion.ALONE,
            PlaceType.ATTRACTION
    );
}
