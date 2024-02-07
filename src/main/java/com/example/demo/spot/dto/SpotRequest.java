package com.example.demo.spot.dto;

import com.example.demo.spot.domain.vo.Location;

public record SpotRequest(
        String displayName, // "소플러스 제주점~"
        String formattedAddress,// "대한민국 ~도 ~시 ~로 111"
        Location location
) {
}