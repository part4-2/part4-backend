package com.example.demo.review.domain.vo;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import com.example.demo.review.exception.WeatherException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Weather implements CodedEnum<String> {
    SUNNY("화창함"),
    RAINY("비가 옴"),
    CLOUDY("구름이 많음"),
    SNOWY("눈 내림"),
    FOGGY("안개가 낌")
    ;

    private final String description;

    Weather(String description) {
        this.description = description;
    }

    public static Weather getInstance(String description){
        return Arrays.stream(values())
                .filter(weather -> weather.description.equals(description))
                .findAny()
                .orElseThrow(WeatherException.WeatherNotExists::new);
    }

    @Override
    public String getCode() {
        return description;
    }
    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<Weather, String>{
        public Converter() {
            super(Weather.class);
        }
    }
}