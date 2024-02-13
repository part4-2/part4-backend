package com.example.demo.spot.application;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.dto.SpotResponse;
import com.example.demo.star.application.StarService;
import com.example.demo.star.domain.Star;
import com.example.demo.star.domain.vo.StarRank;
import com.example.demo.star.dto.StarRequest;
import com.example.demo.star.exception.StarException;
import com.example.demo.user.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SpotStarService {
    private final SpotService spotService;
    private final StarService starService;
    public void rank(StarRequest request, Users users){
        Spot spot = spotService.findById(request.placeId());

        if (starService.existsByUsersAndSpot(users, spot)) {
            throw new StarException.StarRankAlreadyExistsException(users.getId(), spot.getDisplayName());
        }

        starService.save(new Star(
                users,
                spot,
                StarRank.getInstance(request.starRank())
        ));
    }
    @Transactional
    public void updateRank(StarRequest request, Users users){
        Spot spot = spotService.findById(request.placeId());
        Star star = starService.findByUsersAndSpot(users, spot);
        StarRank starRank = StarRank.getInstance(request.starRank());
        star.update(starRank);
    }


    public SpotResponse getInfoByPlaceId(String placeId){
        Spot spot = spotService.findById(placeId);
        Double spotRank = starService.getSpotRank(placeId);
        return SpotResponse.of(spot, spotRank);
    }
}
