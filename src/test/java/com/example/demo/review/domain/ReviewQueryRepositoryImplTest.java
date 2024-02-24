package com.example.demo.review.domain;

import com.example.demo.common.repository.RepositoryTest;
import com.example.demo.global.exception.DateTimeCustomException;
import com.example.demo.global.exception.SortException;
import com.example.demo.review.application.dto.ReviewListDTO;
import com.example.demo.review.application.dto.ReviewListData;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.user.domain.entity.vo.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.demo.common.test_instance.ReviewFixture.REVIEW_ON_SPOT_1_BY_DK;
import static com.example.demo.common.test_instance.ReviewFixture.REVIEW_ON_SPOT_1_BY_DK_ADMIN;
import static com.example.demo.common.test_instance.SpotFixture.SPOT;
import static com.example.demo.common.test_instance.TagFixture.TAG_OF_NONE;
import static com.example.demo.common.test_instance.UserFixture.DK_ADMIN;
import static com.example.demo.common.test_instance.UserFixture.DK_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReviewQueryRepositoryImplTest extends RepositoryTest {
    @Autowired
    ReviewQueryRepositoryImpl reviewQueryRepository;

    private static final String TEST = "TEST";

    @BeforeEach
    void setUp() {
        entityProvider.saveSpot(SPOT);
        entityProvider.saveUser(DK_USER);
        entityProvider.saveUser(DK_ADMIN);
        saveHundredReviewEntities();
    }

    // 리뷰 엔티티 100개 저장, 81-100 엔티티는 좋아요 저장
    void saveHundredReviewEntities() {
        List<Review> list = IntStream.range(0, 100)
                .mapToObj(
                        value -> Review.builder()
                                .title(new Title(TEST + value))
                                .content(new Content(TEST))
                                .tag(TAG_OF_NONE)
                                .users(DK_USER)
                                .spot(SPOT)
                                .visitingTime(LocalDateTime.now())
                                .starRank(StarRank.ZERO)
                                .build()
                ).toList();

        entityProvider.saveAllReviewAndFlush(list);

        // 80 번 째 이후로 저장
        for (int i = 80; i < list.size(); i++) {
            ReviewLike reviewLike = new ReviewLike(new UserId(1L), (long) i);
            entityProvider.saveReviewLike(reviewLike);
        }
    }

    @Test
    void find20ByLikes_SIZE_IS_20() {
        final List<ReviewListData> byLikes = reviewQueryRepository.findByLikes(SortCondition.POPULAR);
        assertThat(byLikes).hasSize(20);
    }

    @Test
    @DisplayName("좋아요 순으로 정렬된다")
    void find20ByLikes() {
        // given
        Review review = entityProvider.saveReview(REVIEW_ON_SPOT_1_BY_DK);
        Review review2 = entityProvider.saveReview(REVIEW_ON_SPOT_1_BY_DK_ADMIN);

        assertThat(review.getId()).isEqualTo(101L);
        assertThat(review2.getId()).isEqualTo(102L);

        // when
        entityProvider.saveReviewLike(new ReviewLike(new UserId(1L), 101L));
        entityProvider.saveReviewLike(new ReviewLike(new UserId(2L), 101L));
        // 102 번째 엔티티도 저장 (생성 내림차순이 아님을 테스트 하기 위함)
        entityProvider.saveReviewLike(new ReviewLike(new UserId(1L), 102L));

        // then
        List<ReviewListData> byLikes = reviewQueryRepository.findByLikes(SortCondition.POPULAR);
        assertThat(byLikes.get(0).reviewId()).isEqualTo(101);
    }

    @Test
    void getListWithSearchCondition_month() {
        List<ReviewListData> reviewList = reviewQueryRepository.getListWithSearchCondition(
                TEST,
                Tag.ofNone(),
                SortCondition.POPULAR,
                LocalDateTime.now().getMonthValue(),
                null,
                1
        );

        reviewList.forEach(
                reviewListDTO -> assertThat(
                        reviewListDTO.visitingTime().getMonthValue()
                ).isEqualTo(
                        LocalDateTime.now().getMonthValue()
                )
        );
    }

    @Test
    void getListWithSearchCondition_time() {
        List<ReviewListData> reviewList = reviewQueryRepository.getListWithSearchCondition(
                TEST,
                Tag.ofNone(),
                SortCondition.POPULAR,
                LocalDateTime.now().getMonthValue(),
                null,
                1
        );

        reviewList.forEach(
                reviewListData -> assertThat(
                        reviewListData.visitingTime().getHour()
                ).isEqualTo(
                        LocalDateTime.now().getHour()
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getListWithSearchCondition_rows(int page) {
        List<ReviewListData> reviewList = reviewQueryRepository.getListWithSearchCondition(
                TEST,
                Tag.ofNone(),
                SortCondition.POPULAR,
                LocalDateTime.now().getMonthValue(),
                null,
                page
        );

        assertThat(reviewList.size()).isEqualTo(24);
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, -2, -1, 0})
    void getListWithSearchCondition_invalid_input_of_page(int page) {
        assertThatThrownBy(() -> reviewQueryRepository.getListWithSearchCondition(
                        TEST,
                        Tag.ofNone(),
                        SortCondition.POPULAR,
                        LocalDateTime.now().getMonthValue(),
                        null,
                        page
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getListWithSearchCondition_tag() {
        List<ReviewListData> reviewList = reviewQueryRepository.getListWithSearchCondition(
                TEST,
                Tag.ofNone(),
                SortCondition.POPULAR,
                LocalDateTime.now().getMonthValue(),
                null,
                1
        );

        reviewList.forEach(
                reviewListData -> assertThat(
                        reviewListData.tagValues()
                ).isEqualTo(
                        Tag.ofNone()
                )
        );
    }

    @Test
    void getListWithSearchCondition_hour() {
        List<ReviewListData> reviewList = reviewQueryRepository.getListWithSearchCondition(
                TEST,
                Tag.ofNone(),
                SortCondition.POPULAR,
                null,
                LocalDateTime.now().getHour(),
                1
        );

        reviewList.forEach(
                reviewListData -> assertThat(
                        reviewListData.visitingTime().getHour()
                ).isEqualTo(
                        LocalDateTime.now().getHour()
                )
        );
    }

    @Test
    void getListWithSearchCondition_invalid_input_of_month() {
        assertThatThrownBy(() -> reviewQueryRepository.getListWithSearchCondition(
                        TEST,
                        Tag.ofNone(),
                        SortCondition.POPULAR,
                        13,
                        null,
                        1
                )
        ).isInstanceOf(DateTimeCustomException.InvalidFormatOfMonthException.class);
    }

    @Test
    void getListWithSearchCondition_invalid_input_of_hour() {
        assertThatThrownBy(() -> reviewQueryRepository.getListWithSearchCondition(
                        TEST,
                        Tag.ofNone(),
                        SortCondition.POPULAR,
                        null,
                        24,
                        1
                )
        ).isInstanceOf(DateTimeCustomException.InvalidFormatOfHourException.class);
    }

    @Test
    void getListWithSearchCondition_invalid_input_of_sortCondition() {
        assertThatThrownBy(() -> reviewQueryRepository.getListWithSearchCondition(
                        TEST,
                        Tag.ofNone(),
                        null,
                        LocalDateTime.now().getMonthValue(),
                        null,
                        1
                )
        ).isInstanceOf(SortException.SortConditionNotFoundException.class);
    }

}