package com.example.demo.user.domain.enums;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender implements CodedEnum<String> {
    MALE("남자"),
    FEMALE("여자");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender getInstance(String name) {
        return Arrays.stream(values())
                .filter(gender -> gender.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String getCode() {
        return name;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<Gender, String> {
        public Converter(){
            super(Gender.class);
        }
    }
}
