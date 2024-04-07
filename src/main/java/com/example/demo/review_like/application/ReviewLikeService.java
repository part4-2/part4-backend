package com.example.demo.review_like.application;

import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review_like.ReviewLikeRepository;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public void like(UserId userId, ReviewId reviewId) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId.value()));

        if (reviewLike.isPresent()) {
            return;
        }

        reviewLikeRepository.save(new ReviewLike(userId, reviewId.value()));
    }

    public void unlike(UserId userId, ReviewId reviewId) {
        reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId.value()))
                // 있을 경우에만 delete
                .ifPresent(reviewLikeRepository::delete);
    }

    public long getCount(ReviewId reviewId){
        return reviewLikeRepository.countReviewLikeByReviewId(reviewId.value());
    }


    public boolean isLiked(Long userId, Long reviewId) {
        return reviewLikeRepository.existsByReviewIdAndUserId(reviewId, userId);
    }
}
