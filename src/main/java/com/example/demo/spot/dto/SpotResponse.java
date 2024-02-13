package com.example.demo.spot.dto;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.vo.Location;

public record SpotResponse ( String placeId,
                             String displayName, // "소플러스 제주점~"
                             String formattedAddress,// "대한민국 ~도 ~시 ~로 111"
                             Location location,
                             Double star
) {
    public static SpotResponse of(Spot spot, Double star){
        return new SpotResponse(
                spot.getPlaceId(),
                spot.getDisplayName(),
                spot.getFormattedAddress(),
                spot.getLocation(),
                star
        );
    }
}
