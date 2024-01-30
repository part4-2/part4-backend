package com.example.demo.review.domain.vo;

import com.example.demo.review.exception.WeatherException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WeatherTest {

    @ParameterizedTest
    @ValueSource(strings = {"FOO", "TEST", "", "날씨?"})
    void getInstanceFailByInvalidValue(String invalidValue) {
        assertThrows(WeatherException.WeatherNotExists.class,() -> Weather.getInstance(invalidValue));
    }

    @Test
    void getInstance() {
        Arrays.stream(Weather.values())
                .forEach(each -> assertThat(each).isEqualTo(Weather.getInstance(each.getDescription())));
    }
}