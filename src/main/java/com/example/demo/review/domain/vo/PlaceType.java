package com.example.demo.review.domain.vo;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import lombok.Getter;

import java.util.Arrays;
@Getter
public enum PlaceType implements CodedEnum<String> {
    FOODS("맛집"),
    SIGHT_SEEING("관광"),
    REST("휴양"),
    ATTRACTION("명소"),
    NONE("모르겠음");

    private final String description;

    PlaceType(String description) {
        this.description = description;
    }

    public static PlaceType getInstance(String description){
        return Arrays.stream(values())
                .filter(placeType -> placeType.description.equals(description))
                .findAny()
                .orElse(NONE);
    }

    @Override
    public String getCode() {
        return description;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<PlaceType, String> {
        public Converter() {
            super(PlaceType.class);
        }
    }
}