package com.example.demo.spot.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.spot.domain.vo.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Spot extends BaseTimeEntity {
    @Id
    @Column(unique = true)
    private String placeId;

    private String displayName; // "소플러스 제주점~"

    @Column(unique = true)
    private String formattedAddress;// "대한민국 ~도 ~시 ~로 111"

    @Embedded
    private Location location;
    @Builder
    public Spot(String placeId, String displayName, String formattedAddress, Location location) {
        this.placeId = placeId;
        this.displayName = displayName;
        this.formattedAddress = formattedAddress;
        this.location = location;
    }
}