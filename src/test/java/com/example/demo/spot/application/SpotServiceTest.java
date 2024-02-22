package com.example.demo.spot.application;

import com.example.demo.common.service.ServiceTest;
import com.example.demo.common.test_instance.SpotFixture;
import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.vo.Location;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.exception.SpotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SpotServiceTest extends ServiceTest {
    @Autowired
    private SpotService spotService;
    private Spot testSpot;
    private static final String UNKNOWN_ID = "UNKNOWN_ID";
    private static final String TEST = "TEST";

    @BeforeEach
    void setUp() {
        testSpot = entityProvider.saveSpot(SpotFixture.SPOT);
    }

    @Test
    void findById() {
        Spot spot = spotService.findById(testSpot.getPlaceId());

        assertEquals(spot, testSpot);
    }

    @Test
    void findById_WITH_UNKKOWN_ID() {
        assertThrows(SpotException.SpotNotFoundException.class,
                () -> spotService.findById(UNKNOWN_ID));
    }

    @Test
    void existsByPlaceId() {
        boolean isSpotExists = spotService.existsByPlaceId(testSpot.getPlaceId());
        assertTrue(isSpotExists);
    }

    @Test
    void existsByPlaceId_FALSE() {
        boolean isSpotExists = spotService.existsByPlaceId(UNKNOWN_ID);
        assertFalse(isSpotExists);
    }

    @Test
    void save() {
        SpotRequest spotRequest = new SpotRequest(
                TEST,
                TEST,
                TEST,
                new Location(
                        TEST,
                        TEST
                )
        );
        Spot saved = spotService.save(spotRequest);

        Spot expected = Spot.builder()
                .placeId(spotRequest.placeId())
                .location(spotRequest.location())
                .formattedAddress(spotRequest.formattedAddress())
                .displayName(spotRequest.name())
                .build();

        assertEquals(saved, expected);
    }
}