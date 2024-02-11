package com.example.demo.common.test_instance;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.vo.Location;

public class SpotFixture {
    private static final String TEST = "TEST";
    private static final String FOO = "FOO";
    private static final String TEST_ID = "121251asg2a5qwtq125";
    private static final String TEST_LATITUDE = "33.489793999999996";
    private static final String TEST_LONGITUDE = "41.489182847399996";

    public static final Spot FOO_SPOT = new Spot(
            TEST_ID,
            FOO,
            FOO,
            new Location(TEST_LATITUDE,
                    TEST_LONGITUDE)
    );

    public static final Spot SPOT = new Spot(
            TEST_ID,
            TEST,
            TEST,
            new Location(TEST_LATITUDE,
                    TEST_LONGITUDE)
    );
}
