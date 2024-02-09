package com.example.demo.common;

import com.example.demo.review.domain.Review;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryFactory {
    @Autowired
    private Repositories repositories;

    public Users saveUser(Users users){
        return repositories.getUserRepository().save(users);
    }
    public Spot saveSpot(Spot spot){
        return repositories.getSpotRepository().save(spot);
    }

    public Review saveReview(Review review){
        return repositories.getReviewRepository().save(review);
    }

    public List<Review> saveAllReviewAndFlush(List<Review> reviewList){
        return repositories.getReviewRepository().saveAllAndFlush(reviewList);
    }

    public ReviewLike saveReviewLike(ReviewLike reviewLike){
        return repositories.getReviewLikeRepository().save(reviewLike);
    }
}
