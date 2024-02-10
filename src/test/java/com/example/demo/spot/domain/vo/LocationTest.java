package com.example.demo.spot.domain.vo;

import com.example.demo.spot.exception.SpotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocationTest {
    // 의미없는 두 값
    private static final String TEST_VALUE = "41.489182847399996"; // lenght 18
    private static final String TEST_VALUE_2 = "31.489182847399996"; // lenght 18
    @Test
    void validateLocationLength() {
        assertThrows(SpotException.LocationLengthException.class,
                () -> new Location(TEST_VALUE, TEST_VALUE + 1)); // lenght 19
    }

    @Test
    void validateNull() {
        assertThrows(NullPointerException.class,
                () -> new Location(TEST_VALUE, null));
    }

    @Test
    void validateEmpty() {
        assertThrows(SpotException.LocationBlankException.class,
                () -> new Location(TEST_VALUE, ""));
    }

    @Test
    void initTest() {
        Location location = new Location(TEST_VALUE, TEST_VALUE_2);
        assertEquals(TEST_VALUE, location.getLatitude());
        assertEquals(TEST_VALUE_2, location.getLongitude());
    }
}