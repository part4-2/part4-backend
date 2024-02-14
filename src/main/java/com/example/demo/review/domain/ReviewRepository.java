package com.example.demo.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {
    @Query("SELECT COALESCE(AVG(r.starRank), 0) FROM Review r WHERE r.spot.placeId = :placeId")
    Double getAverageStarByPlaceId(@Param(value = "placeId") String placeId);
}