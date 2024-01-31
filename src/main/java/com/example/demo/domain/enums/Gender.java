package com.example.demo.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender getInstanceOf(String name) {
        return Arrays.stream(values())
                .filter(gender -> gender.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
