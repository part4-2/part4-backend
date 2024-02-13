package com.example.demo.review_like.application;

import com.example.demo.review.application.ReviewService;
import com.example.demo.review.application.dto.ReviewWithLike;
import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review_like.ReviewLikeRepository;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.user.domain.entity.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewService reviewService;

    // TODO: 2/9/24 이 두 매서드에서 throw를 할지 말지 고민해보자 (무조건 200으로 줄지?)
    //  더불어서 동시성문제를 생각할지?
    public void like(UserId userId, ReviewId reviewId) {
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId.value()));

        if (reviewLike.isPresent()) {
            return;
        }

        reviewLikeRepository.save(new ReviewLike(
                userId,
                reviewId.value()
        ));
    }

    public void unlike(UserId userId, ReviewId reviewId) {
        reviewLikeRepository.findById(new ReviewLikeId(userId, reviewId.value()))
                // 있을 경우에만 delete
                .ifPresent(reviewLikeRepository::delete);
    }

    public int getCount(ReviewId reviewId){
        return reviewLikeRepository.countReviewLikeByReviewId(reviewId.value());
    }
    public ReviewWithLike getOneWithLikes(final ReviewId reviewId){
        Review review = reviewService.findById(reviewId);
        int count = this.getCount(reviewId);

        return ReviewWithLike.of(review, count);
    }

    public List<ReviewWithLike> getPopularLists(){

        return reviewService.findByLikes()
                .stream()
                .map(
                        review -> ReviewWithLike.of(review, getCount(new ReviewId(review.getId())))
                )
                .toList();
    }
}
