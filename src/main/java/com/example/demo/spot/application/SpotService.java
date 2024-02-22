package com.example.demo.spot.application;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.SpotRepository;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.exception.SpotException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Log4j2
public class SpotService {
    private final SpotRepository spotRepository;

    public Spot findById(String id) {
        return spotRepository.findById(id)
                .orElseThrow(
                        () -> new SpotException.SpotNotFoundException(id)
                );
    }

    public Spot save(SpotRequest spotRequest) {
        Spot spot = Spot.builder()
                .placeId(spotRequest.placeId())
                .location(spotRequest.location())
                .formattedAddress(spotRequest.formattedAddress())
                .displayName(spotRequest.name())
                .build();

        return spotRepository.save(spot);
    }

    public boolean existsByPlaceId(String placeId){
        return spotRepository.existsSpotByPlaceId(placeId);
    }
}