package com.example.demo.spot.dto;

import com.example.demo.spot.domain.Spot;

public record SpotResponse ( String placeId,
                             String displayName, // "소플러스 제주점~"
                             String formattedAddress,// "대한민국 ~도 ~시 ~로 111"
                             String latitude,
                             String longitude,
                             Double star
) {
    public static SpotResponse of(Spot spot, Double star){
        return new SpotResponse(
                spot.getPlaceId(),
                spot.getDisplayName(),
                spot.getFormattedAddress(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude(),
                star
        );
    }
}
