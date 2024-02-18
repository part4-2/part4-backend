package com.example.demo.review.domain.vo;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import com.example.demo.review.exception.StarException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StarRank implements CodedEnum<Double> {
    ZERO(0),
    ZERO_POINT_FIVE(0.5),
    ONE(1),
    ONE_POINT_FIVE(1.5),
    TWO(2),
    TWO_POINT_FIVE(2.5),
    THREE(3),
    THREE_POINT_FIVE(3.5),
    FOUR(4),
    FOUR_POINT_FIVE(4.5),
    FIVE(5);

    private final double value;

    StarRank(double value) {
        this.value = value;
    }

    public static StarRank getInstance(Double value){
        if (value == null){
            return StarRank.ZERO;
        }
        return Arrays.stream(values())
                .filter(star -> star.value == value)
                .findAny()
                .orElseThrow(
                        () -> new StarException.InvalidStarRankException(value)
                );
    }


    @Override
    public Double getCode() {
        return value;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<StarRank, Double> {
        public Converter() {
            super(StarRank.class);
        }
    }
}