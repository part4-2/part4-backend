package com.example.demo.common;

import com.example.demo.review.domain.ReviewRepository;
import com.example.demo.review_like.ReviewLikeRepository;
import com.example.demo.spot.domain.SpotRepository;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repositories {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public SpotRepository getSpotRepository() {
        return spotRepository;
    }

    public ReviewLikeRepository getReviewLikeRepository() {
        return reviewLikeRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
