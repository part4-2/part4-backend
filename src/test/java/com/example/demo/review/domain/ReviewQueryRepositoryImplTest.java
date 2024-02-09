package com.example.demo.review.domain;

import com.example.demo.common.RepositoryTest;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.review_like.domain.ReviewLike;
import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.domain.vo.Location;
import com.example.demo.user.domain.entity.Role;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.entity.vo.UserId;
import com.example.demo.user.domain.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewQueryRepositoryImplTest extends RepositoryTest {
    @Autowired
    ReviewQueryRepositoryImpl reviewQueryRepository;
    private static final String TEST_DISPLAY_NAME = "TEST";
    private static final String TEST = "TEST";
    private static final String TEST_LATITUDE = "33.489793999999996";
    private static final String TEST_LONGITUDE = "41.489182847399996";

    private static final Spot SPOT = new Spot(
            TEST_DISPLAY_NAME,
            TEST,
            new Location(TEST_LATITUDE,
                    TEST_LONGITUDE)
    );

    private static final Users USERS = new Users("email",
            "nickname",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Role.USER
    );

    private static final Users USERS2 = new Users("email",
            "nickname",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Role.USER
    );

    private static final Review REVIEW = new Review(
            new Title(TEST),
            new Content(TEST),
            Weather.SNOWY,
            USERS,
            SPOT
    );

    @BeforeEach
    void setUp() {
        repositoryFactory.saveSpot(SPOT);
        repositoryFactory.saveUser(USERS);
        repositoryFactory.saveUser(USERS2);
        saveHundredReviewEntities();
    }

    // 리뷰 엔티티 100개 저장, 81-100 엔티티는 좋아요 저장
    void saveHundredReviewEntities() {
        List<Review> list = IntStream.range(0, 100)
                .mapToObj(
                        value -> {
                            Review review = new Review(
                                    new Title(TEST + value),
                                    new Content(TEST),
                                    Weather.SNOWY,
                                    USERS,
                                    SPOT
                            );
                            return review;
                        }
                ).toList();

        repositoryFactory.saveAllReviewAndFlush(list);

        // 80 번 째 이후로 저장
        for (int i = 80; i < list.size(); i++) {
            ReviewLike reviewLike = new ReviewLike(new UserId(1L), new ReviewId((long) i));
            repositoryFactory.saveReviewLike(reviewLike);
        }
    }

    @Test
    void find20ByLikes_SIZE_IS_20() {
        final List<Review> byLikes = reviewQueryRepository.findByLikes();
        assertThat(byLikes).hasSize(20);
    }

    @Test
    @DisplayName("좋아요 순으로 정렬된다")
    void find20ByLikes() {
        // given
        Review review = repositoryFactory.saveReview(REVIEW);
        Review review2 = repositoryFactory.saveReview(REVIEW);
        // when
        repositoryFactory.saveReviewLike(new ReviewLike(new UserId(1L), new ReviewId(101L)));
        repositoryFactory.saveReviewLike(new ReviewLike(new UserId(2L), new ReviewId(101L)));
        // 102 번째 엔티티도 저장 (생성 내림차순이 아님을 테스트 하기 위함)
        repositoryFactory.saveReviewLike(new ReviewLike(new UserId(1L), new ReviewId(102L)));

        // then
        List<Review> byLikes = reviewQueryRepository.findByLikes();
        assertThat(byLikes.get(0).getId()).isEqualTo(101);
    }

    @Test
    @DisplayName("좋아요 수가 동률이라면 최신순으로 정렬한다.")
    void find20ByLikes_ORDERS() {
        List<Review> byLikes = reviewQueryRepository.findByLikes();

        Review review = byLikes.get(0); // 맨 첫번째 유닛, 계속 재할당된다
        for (int i = 1; i < byLikes.size(); i++) {
            // 좋아요 수는 현재 동률이므로, 먼저 오는 녀석의 날짜는
            LocalDateTime first = review.getCreatedDate();
            // 나중에 오는 녀석의 날짜보다
            LocalDateTime last = byLikes.get(i).getCreatedDate();
            // 항상 앞선다 (나중이다)
            assertThat(first.isAfter(last)).isTrue();
            // 처음에 올 녀석을 재할당한다
            review = byLikes.get(i);
        }
    }
}