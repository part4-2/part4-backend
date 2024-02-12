package com.example.demo.review.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findByLikes() {
        return queryFactory.selectFrom(review)
                .leftJoin(reviewLike)
                .on(reviewLike.reviewId.eq(review.id))
                .groupBy(review.id) // 리뷰의 id로 그룹화하여
                .orderBy(reviewLike.reviewId.count().desc(), review.createdDate.desc()) // reviewLike의 개수로 내림차순 정렬
                .limit(20) // 상위 20개 리뷰만 선택
                .fetch();
    }
}