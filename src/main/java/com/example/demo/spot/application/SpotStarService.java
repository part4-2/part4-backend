package com.example.demo.spot.application;

import com.example.demo.review.application.ReviewService;
import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.dto.SpotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpotStarService {
    private final SpotService spotService;
    private final ReviewService reviewService;


    public SpotResponse getInfoByPlaceId(String placeId){
        Spot spot = spotService.findById(placeId);
        Double spotRank = reviewService.getAverageStarRank(placeId);
        return SpotResponse.of(spot, spotRank);
    }

    public SpotResponse saveOrGet(SpotRequest spotRequest){
        String placeId = spotRequest.placeId();
        boolean isSpotAlreadyExists = spotService.existsByPlaceId(placeId);

        if (isSpotAlreadyExists){
            Spot spotAlreadyExists = spotService.findById(placeId);
            Double spotRank = reviewService.getAverageStarRank(placeId);
            return SpotResponse.of(spotAlreadyExists, spotRank);
        }

        Spot savedSpot = spotService.save(spotRequest);

        return SpotResponse.of(savedSpot, StarRank.ZERO.getValue());
    }
}
