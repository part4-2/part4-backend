package com.example.demo.review.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WeatherTest {

    @ParameterizedTest
    @ValueSource(strings = {"FOO", "TEST", "", "날씨?"})
    void getInstanceFailByInvalidValue(String invalidValue) {
        assertEquals(Weather.NONE,Weather.getInstance(invalidValue) );
//        assertThrows(WeatherException.WeatherNotFoundException.class,() -> Weather.getInstance(invalidValue));
    }

    @Test
    void getInstance() {
        Arrays.stream(Weather.values())
                .forEach(each -> assertThat(each).isEqualTo(Weather.getInstance(each.getDescription())));
    }
}