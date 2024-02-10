package com.example.demo.spot.domain.vo;

import com.example.demo.spot.exception.SpotException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Location {
    private static final int MAX_LENGTH = 18; // 제한 길이

    private String latitude; // 위도
    private String longitude; // 경도

    public Location(String latitude, String longitude) {
        validateNull(latitude, longitude);
        validate(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static void validateNull(final String latitude, final String longitude) {
        if (Objects.isNull(latitude) || Objects.isNull(longitude)) {
            throw new NullPointerException("좌표 중 null이 있습니다.");
        }
    }

    private static void validate(final String latitude, final String longitude) {
        if (latitude.length() > MAX_LENGTH || longitude.length() > MAX_LENGTH) {
            throw new SpotException.LocationLengthException(MAX_LENGTH, latitude, longitude);
        }
        if (latitude.isBlank() || longitude.isBlank()) {
            throw new SpotException.LocationBlankException();
        }
    }
}