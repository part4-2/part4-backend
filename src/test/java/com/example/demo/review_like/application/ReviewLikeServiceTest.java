package com.example.demo.review_like.application;

import com.example.demo.common.service.ServiceTest;
import com.example.demo.common.test_instance.ReviewFixture;
import com.example.demo.common.test_instance.ReviewLikeFixture;
import com.example.demo.review.application.dto.ReviewListDTO;
import com.example.demo.review.application.dto.ReviewWithLike;
import com.example.demo.review.domain.Review;
import com.example.demo.review.domain.ReviewRepository;
import com.example.demo.review.domain.vo.*;
import com.example.demo.review_like.ReviewLikeRepository;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.review_like.domain.vo.ReviewLikeId;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.entity.vo.UserId;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.example.demo.common.test_instance.ReviewFixture.REVIEW_ON_SPOT_1_BY_DK;
import static com.example.demo.common.test_instance.ReviewLikeFixture.REVIEW_LIKE_ID_BY_DK;
import static com.example.demo.review.application.dto.SortCondition.POPULAR;
import static com.example.demo.review.domain.vo.Companion.FRIEND;
import static com.example.demo.review.domain.vo.PlaceType.FOODS;
import static com.example.demo.review.domain.vo.Weather.SUNNY;
import static com.example.demo.user.domain.enums.Gender.FEMALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ReviewLikeServiceTest extends ServiceTest {
    @Autowired
    private ReviewLikeService reviewLikeService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @Nested
    @DisplayName("리뷰 좋아요 - like() 매서드 테스트")
    class LikeTest {
        @Test
        @DisplayName("이미 좋아요 했다면 새로운 좋아요 객체가 만들어지지 않는다.")
        void fail_duplicatedLike() {
            // given
            ReviewLike likeByDk = ReviewLikeFixture.REVIEW_LIKE_BY_DK;
            reviewLikeRepository.save(likeByDk);

            // when
            reviewLikeService.like(new UserId(1L), new ReviewId(1L));

            // then
            assertThat(reviewLikeRepository.findAll()).hasSize(1);
        }

        @Test
        @DisplayName("좋아요에 성공한다.")
        void success_duplicatedLike() {
            // given
            ReviewLike likeByDk = ReviewLikeFixture.REVIEW_LIKE_BY_DK;
            reviewLikeRepository.save(likeByDk);

            // when
            reviewLikeService.like(new UserId(2L), new ReviewId(2L));

            // then
            assertThat(reviewLikeRepository.findAll()).hasSize(2);
        }
    }

    @Nested
    @DisplayName("리뷰 좋아요 취소 테스트")
    class UnlikeTest {
        @Test
        @DisplayName("리뷰 좋아요가 존재하지 않을 때에는 아무 일도 일어나지 않는다.")
        void notExistLike() {
            assertThat(reviewLikeRepository.findAll()).isEmpty();
        }

        @Test
        @DisplayName("리뷰 좋아요가 존재하면 해당 좋아요를 삭제한다.")
        void unlike() {
            // given
            ReviewLike likeByIh = ReviewLikeFixture.REVIEW_LIKE_BY_IH;
            reviewLikeRepository.save(likeByIh);
            assertThat(reviewLikeRepository.findAll()).hasSize(1);

            // when
            reviewLikeService.unlike(new UserId(2L), new ReviewId(2L));

            // then
            assertThat(reviewLikeRepository.findAll()).isEmpty();
        }
    }


    @Nested
    @DisplayName("주어진 아이디가 이미 존재하는지 여부 테스트")
    class isLikedTest {

        @Test
        @DisplayName("좋아요가 존재하지 않을 때 False를 리턴한다.")
        void notExistLike() {
            // given
            ReviewLikeId reviewLikeIdByDk = REVIEW_LIKE_ID_BY_DK;
            assertThat(reviewLikeRepository.findById(reviewLikeIdByDk)).isEmpty();

            // when
            boolean isLiked = reviewLikeService.isLiked(REVIEW_LIKE_ID_BY_DK.getUserId(), REVIEW_LIKE_ID_BY_DK.getReviewId());

            // then
            assertThat(isLiked).isFalse();
        }

        @Test
        @DisplayName("좋아요가 존재할 때 True를 리턴한다.")
        void testTwo() {
            //given
            ReviewLike likeByDk = ReviewLikeFixture.REVIEW_LIKE_BY_DK;
            reviewLikeRepository.save(likeByDk);

            //then
            ReviewLikeId reviewLikeIdByDk = REVIEW_LIKE_ID_BY_DK;
            assertThat(reviewLikeService.isLiked(reviewLikeIdByDk.getUserId(), reviewLikeIdByDk.getReviewId())).isTrue();
        }

    }

    @Test
    @DisplayName("리뷰 좋아요 수 테스트")
    void reviewLikeCountTest() {
        // given
        ReviewLike likeBySI = ReviewLikeFixture.REVIEW_LIKE_BY_SI;
        ReviewLike likeByDk = ReviewLikeFixture.REVIEW_LIKE_BY_DK;
        reviewLikeRepository.save(likeBySI);
        reviewLikeRepository.save(likeByDk);

        // when & then
        assertEquals(reviewLikeService.getCount(new ReviewId(1L)), 2L);
    }

    @Test
    @DisplayName("리뷰 ID 리뷰 정보 확인")
    void retrieveOneReviewTest() {
        // given
        Review review = ReviewFixture.REVIEW_ON_SPOT_1_BY_DK;
        reviewRepository.save(review);

        // when
        ReviewWithLike like = reviewLikeService.getOneWithLikes(new ReviewId(1L));

        // then
        assertThat(like.nickName()).isEqualTo("DK");
        assertThat(like.title()).isEqualTo("TEST");
        assertThat(like.spotName()).isEqualTo("TEST");
    }

    //20개 리뷰 조회
    @Test
    void mainReviewListTest(){

        // given
        Review review1 = ReviewFixture.REVIEW_OF_ALL_FIELD_VALUE_IS_FOO;
        Review review2 = ReviewFixture.REVIEW_ON_SPOT_1_BY_DK;
        reviewRepository.save(review1);
        reviewRepository.save(review2);

        ReviewLike likeBySI = ReviewLikeFixture.REVIEW_LIKE_BY_SI;
        ReviewLike likeByDk = ReviewLikeFixture.REVIEW_LIKE_BY_DK;
        ReviewLike likeByIH = ReviewLikeFixture.REVIEW_LIKE_BY_IH;
        reviewLikeRepository.save(likeBySI);
        reviewLikeRepository.save(likeByDk);
        reviewLikeRepository.save(likeByIH);

        //when
        List<ReviewListDTO> reviewList = reviewLikeService.getMainReviewList(POPULAR);

        //then
        assertThat(reviewList.get(0).nickName()).isEqualTo("FOO");
        assertThat(reviewList.get(0).title()).isEqualTo("FOO");
        assertThat(reviewList.get(1).nickName()).isEqualTo("DK");
        assertThat(reviewList.get(1).title()).isEqualTo("TEST");
    }

}


