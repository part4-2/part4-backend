package com.example.demo.review.application;

import com.example.demo.review.application.dto.ReviewRequest;
import com.example.demo.review.application.dto.ReviewResponseDTO;
import com.example.demo.review.application.dto.ReviewWriteRequest;
import com.example.demo.review.application.dto.TagValues;
import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.ReviewRepository;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.exception.ReviewException;
import com.example.demo.spot.application.SpotService;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final SpotService spotService;
    private final UserService userService;

    public Review findById(ReviewId reviewId){
        return reviewRepository.findById(reviewId.value())
                .orElseThrow(
                        () -> new ReviewException.ReviewNotFoundException(reviewId.value())
                );
    }
    @Transactional
    public Long write(final ReviewRequest reviewRequest,
                      final String nickName,
                      final String spotId,
                      final TagValues requestTag,
                      final LocalDateTime visitingTime){
        final String title = reviewRequest.title();
        final String content = reviewRequest.content();
        final Spot spot = spotService.findById(spotId);
        final Users user = userService.findByNickName(nickName);

        final Tag tag = getTag(requestTag);

        final Review review = Review.builder()
                .title(new Title(title))
                .content(new Content(content))
                .users(user)
                .spot(spot)
                .tag(tag)
                .visitingTime(visitingTime)
                .build();

        Review savedReview = reviewRepository.save(review);
        return savedReview.getId();
    }

    private static Tag getTag(TagValues requestTag) {
        if (requestTag == null){
             return Tag.ofNone();
        }

        return Tag.of(requestTag);
    }

    @Transactional
    public void updateReview(final Long reviewId,
                             final ReviewWriteRequest reviewRequest,
                             final TagValues tagValues,
                             final LocalDateTime visitingTime){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new ReviewException.ReviewNotFoundException(reviewId)
                );

        review.update(getTag(tagValues),
                new Title(reviewRequest.title()),
                new Content(reviewRequest.content()),
                visitingTime
        );

        reviewRepository.save(review);
    }


    public ReviewResponseDTO getOneById(final Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new ReviewException.ReviewNotFoundException(reviewId)
                );

        return ReviewResponseDTO.of(review);
    }


    public List<Review> findByLikes(){
        return reviewRepository.findByLikes();
    }


    public void deleteReview(Long id){
        // TODO: 2/7/24 삭제 부분 좋아요 등은 남길지? 그렇다면 FK를 사용할지? 혹은 소프트딜리트 할지?
    }
}
