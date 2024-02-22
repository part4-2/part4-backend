package com.example.demo.review_like.application;

import com.example.demo.global.utils.DateUtils;
import com.example.demo.review.application.ReviewService;
import com.example.demo.review.application.dto.*;
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

    public List<ReviewListDTO> getMainReviewList(SortCondition order){

        List<ReviewListData> dataFromRepository = reviewService.findByLikes(order);

        return dataFromRepository.stream()
                .map(
                        data -> new ReviewListDTO(
                                data.reviewId(),
                                data.title().getValue(),
                                TagValues.of(data.tagValues()),
                                data.nickName(),
                                DateUtils.parseTimeToString(data.visitingTime()),
                                data.stars().getValue(),
                                data.image())
                )
                .toList();
    }
}
