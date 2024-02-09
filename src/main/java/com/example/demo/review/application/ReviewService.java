package com.example.demo.review.application;

import com.example.demo.review.application.dto.ReviewRequest;
import com.example.demo.review.application.dto.ReviewResponseDTO;
import com.example.demo.review.application.dto.ReviewWriteRequest;
import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.ReviewRepository;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.SearchCondition;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.review.exception.ReviewException;
import com.example.demo.review_tag.domain.vo.TagName;
import com.example.demo.spot.application.SpotService;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;


import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final SpotService spotService;
    private final UserService userService;

    @Transactional
    public Long write(final ReviewRequest reviewRequest,
                      final String nickName,
                      final Long spotId,
                      final Weather weather){
        final String title = reviewRequest.title();
        final String content = reviewRequest.content();
        final Spot spot = spotService.findById(spotId);
        final Users user = userService.findByNickName(nickName);

        final Review review = Review.builder()
                .title(new Title(title))
                .content(new Content(content))
                .users(user)
                .spot(spot)
                .weather(weather)
                .build();

        Review savedReview = reviewRepository.save(review);
        return savedReview.getId();
    }
    @Transactional
    public void updateReview(final Long reviewId,
                             final ReviewWriteRequest reviewRequest,
                             final Weather weather){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new ReviewException.ReviewNotFoundException(reviewId)
                );
        review.update(weather,
                new Title(reviewRequest.title()),
                new Content(reviewRequest.content())
        );

        reviewRepository.save(review);
    }
    public List<ReviewResponseDTO> findByTagNames(final List<TagName> tags, final SearchCondition searchCondition){
        return reviewRepository.findByTagNames(tags, searchCondition)
                .stream()
                .map(ReviewResponseDTO::of)
                .toList();
    }

    public ReviewResponseDTO getOneById(final Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new ReviewException.ReviewNotFoundException(reviewId)
                );

        return ReviewResponseDTO.of(review);
    }
    public List<ReviewResponseDTO> findByLikes(){
        return reviewRepository.findByLikes()
                .stream()
                .map(ReviewResponseDTO::of)
                .toList();
    }

    public void deleteReview(Long id){
        // TODO: 2/7/24 삭제 부분 좋아요 등은 남길지? 그렇다면 FK를 사용할지? 혹은 소프트딜리트 할지?
    }
}
