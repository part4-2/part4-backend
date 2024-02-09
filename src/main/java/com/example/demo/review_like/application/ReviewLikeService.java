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

    // TODO: 2/9/24 이 두 매서드에서 throw를 할지 말지 고민해보자 (무조건 200으로 줄지?)
    //  더불어서 동시성문제를 생각할지?
    public void like(UserId userId, ReviewId reviewId) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId));

        if (reviewLike.isPresent()) {
            return;
        }

        reviewLikeRepository.save(new ReviewLike(
                userId,
                reviewId
        ));
    }

    public void unlike(UserId userId, ReviewId reviewId) {
       reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId))
                // 있을 경우에만 delete
                .ifPresent(reviewLikeRepository::delete);
    }
}
