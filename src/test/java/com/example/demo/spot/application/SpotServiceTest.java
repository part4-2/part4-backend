package com.example.demo.spot.application;

import com.example.demo.common.RepositoryTest;
import com.example.demo.common.test_instance.SpotFixture;
import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.SpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SpotServiceTest extends RepositoryTest {
    @Autowired
    protected SpotRepository spotRepository;

    @Test
    void findById() {
        Spot save = spotRepository.save(SpotFixture.SPOT);
        Spot spot = spotRepository.findById(SpotFixture.SPOT.getPlaceId()).orElseThrow();
        assertThat(spot).isEqualTo(save);
    }

    @Test
    void findByAddress() {
        Spot save = spotRepository.save(SpotFixture.SPOT);
        Spot spot = spotRepository.findByFormattedAddress(SpotFixture.SPOT.getFormattedAddress())
                .orElseThrow();
        assertThat(spot).isEqualTo(save);
    }
}