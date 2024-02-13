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

    @Transactional
    public String save(SpotRequest spotRequest) {
        spotRepository.findByFormattedAddress(spotRequest.formattedAddress())
                .ifPresent(
                        a -> {throw new SpotException.DuplicatedSpotExistsException(spotRequest.formattedAddress());}
                );

        Spot spot = Spot.builder()
                .placeId(spotRequest.placeId())
                .location(spotRequest.location())
                .formattedAddress(spotRequest.formattedAddress())
                .displayName(spotRequest.displayName())
                .build();

        Spot savedSpot = spotRepository.save(spot);
        return savedSpot.getPlaceId();
    }
}