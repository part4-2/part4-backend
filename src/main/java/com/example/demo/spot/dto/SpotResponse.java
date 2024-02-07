package com.example.demo.spot.dto;

import com.example.demo.spot.domain.Spot;

public record SpotResponse ( Long id,
                             String displayName, // "소플러스 제주점~"
                             String formattedAddress,// "대한민국 ~도 ~시 ~로 111"
                             Double latitude,
                             Double longitude
) {
    public static SpotResponse of(Spot spot){
        return new SpotResponse(
                spot.getId(),
                spot.getDisplayName(),
                spot.getFormattedAddress(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude()
        );
    }
}
