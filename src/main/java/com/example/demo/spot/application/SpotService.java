package com.example.demo.spot.application;

import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.SpotRepository;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.dto.SpotResponse;
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

    public Spot findById(Long id) {
        return spotRepository.findById(id)
                .orElseThrow(
                        () -> new SpotException.SpotNotFoundException(id)
                );
    }

    public SpotResponse findByAddress(String formattedAddress) {
        Spot spot = spotRepository.findByFormattedAddress(formattedAddress)
                .orElseThrow(
                        () -> new SpotException.SpotNotFoundException(formattedAddress)
                );

        return SpotResponse.of(spot);
    }
    @Transactional
    public Long save(SpotRequest spotRequest) {
        spotRepository.findByFormattedAddress(spotRequest.formattedAddress())
                .ifPresent(
                        a -> {throw new SpotException.DuplicatedSpotExistsException(spotRequest.formattedAddress());}
                );

        Spot spot = Spot.builder()
                .location(spotRequest.location())
                .formattedAddress(spotRequest.formattedAddress())
                .displayName(spotRequest.displayName())
                .build();

        Spot savedSpot = spotRepository.save(spot);
        return savedSpot.getId();
    }
}