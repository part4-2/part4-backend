package com.example.demo.star.domain;

import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {
    boolean existsByUsersAndSpot(Users users, Spot spot);
    Optional<Star> findByUsersAndSpot(Users users, Spot spot);
    @Query("SELECT AVG(s.starRank) FROM Star s WHERE s.spot.placeId = :placeId")
    Optional<Double> getAverageStarRank(@Param(value = "placeId") String placeId);
}
