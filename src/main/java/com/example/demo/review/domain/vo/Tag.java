package com.example.demo.review.domain.vo;

import com.example.demo.review.application.dto.TagValues;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Tag {
    private Weather weather;
    private Companion companion;
    private PlaceType placeType;

    public Tag(Weather weather, Companion companion, PlaceType placeType) {
        this.weather = weather;
        this.companion = companion;
        this.placeType = placeType;
    }

    public static Tag ofNone(){
        return new Tag(
                Weather.NONE,
                Companion.NONE,
                PlaceType.NONE
        );
    }

    public static Tag of(TagValues tagValues) {
        Weather weather = Weather.getInstance(tagValues.weather());
        Companion companion = Companion.getInstance(tagValues.companion());
        PlaceType placeType = PlaceType.getInstance(tagValues.placeType());
        return new Tag(weather, companion, placeType);
    }
}