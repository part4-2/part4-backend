package com.example.demo.spot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, String> {
    Optional<Spot> findByFormattedAddress(String formattedAddress);
}
