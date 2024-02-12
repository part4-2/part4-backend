package com.example.demo.star.application;

import com.example.demo.spot.domain.Spot;
import com.example.demo.star.domain.Star;
import com.example.demo.star.domain.StarRepository;
import com.example.demo.star.exception.StarException;
import com.example.demo.user.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarService {
    private final StarRepository starRepository;

    public Star save(Star star){
        return starRepository.save(star);
    }

    public Double getSpotRank(String placeId){
        return starRepository.getAverageStarRank(placeId)
                .orElse((double) 0);
    }

    public boolean existsByUsersAndSpot(Users users, Spot spot){
        return starRepository.existsByUsersAndSpot(users, spot);
    }

    public Star findByUsersAndSpot(Users users, Spot spot){
        return starRepository.findByUsersAndSpot(users, spot)
                .orElseThrow(
                        () -> new StarException.StarNotFoundException(
                                users.getId(),
                                spot.getDisplayName()
                        )
                );
    }
}