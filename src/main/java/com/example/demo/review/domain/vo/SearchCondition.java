package com.example.demo.review.domain.vo;

import java.util.Arrays;

public enum SearchCondition {
    LIKES,
    NEW,
    NONE;

    public static SearchCondition getInstance(String name){
        return Arrays.stream(values())
                .filter(searchCondition -> searchCondition.name().equals(name))
                .findAny()
                .orElse(NONE);
    }
}
