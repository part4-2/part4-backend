package com.example.demo.common.test_instance;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.vo.Location;

public class SpotFixture {
    public static final String TEST_DISPLAY_NAME = "TEST";
    public static final String TEST = "TEST";
    public static final String TEST_LATITUDE = "33.489793999999996";
    public static final String TEST_LONGITUDE = "41.489182847399996";

    public static final Spot SPOT = new Spot(
            TEST_DISPLAY_NAME,
            TEST,
            new Location(TEST_LATITUDE,
                    TEST_LONGITUDE)
    );
}
