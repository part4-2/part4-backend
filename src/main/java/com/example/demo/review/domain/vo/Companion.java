package com.example.demo.review.domain.vo;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import lombok.Getter;

import java.util.Arrays;
@Getter
public enum Companion implements CodedEnum<String> {
    FAMILY("가족"),
    FRIEND("친구"),
    LOVER("연인"),
    ALONE("혼자"),
    NONE("모르겠음");
    private final String description;

    Companion(String description) {
        this.description = description;
    }

    public static Companion getInstance(String description){
        return Arrays.stream(values())
                .filter(companion -> companion.description.equals(description))
                .findAny()
                .orElse(NONE);
    }

    @Override
    public String getCode() {
        return description;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<Companion, String> {
        public Converter() {
            super(Companion.class);
        }
    }
}
