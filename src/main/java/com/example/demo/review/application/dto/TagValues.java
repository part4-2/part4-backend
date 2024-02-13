package com.example.demo.review.application.dto;

import com.example.demo.review.domain.vo.Tag;

public record TagValues(
        String weather,
        String companion,
        String placeType
) {
    public static TagValues of(Tag tag) {
       return new TagValues(
               tag.getWeather().getDescription(),
               tag.getCompanion().getDescription(),
               tag.getPlaceType().getDescription()
       );
    }
}
