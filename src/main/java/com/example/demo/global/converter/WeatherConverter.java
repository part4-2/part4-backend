package com.example.demo.global.converter;

import com.example.demo.review.domain.vo.Weather;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;

public class WeatherConverter implements Converter<String, Weather> {
    @Override
    public Weather convert(@Nonnull String name) {
        return Weather.getInstance(name);
    }
}
