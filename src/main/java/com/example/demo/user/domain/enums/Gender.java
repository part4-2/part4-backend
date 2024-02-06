package com.example.demo.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String name;

    public static Gender getInstance(String name) {
        return Arrays.stream(values())
                .filter(gender -> gender.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("성별은 남자 / 여자 여야합니다."));
    }

}
