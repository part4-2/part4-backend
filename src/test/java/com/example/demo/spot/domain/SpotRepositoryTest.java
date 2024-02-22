package com.example.demo.spot.domain;

import com.example.demo.common.repository.RepositoryTest;
import com.example.demo.common.test_instance.SpotFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SpotRepositoryTest extends RepositoryTest {
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