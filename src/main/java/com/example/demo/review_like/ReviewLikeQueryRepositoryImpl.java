package com.example.demo.review_like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.review_like.domain.QReviewLike.reviewLike;

@Repository
@RequiredArgsConstructor
public class ReviewLikeQueryRepositoryImpl implements ReviewLikeQueryRepository{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Long> getReviewByLikesDesc(){
        return queryFactory
                .select(reviewLike.review.id)
                .from(reviewLike)
                .groupBy(reviewLike.review.id)
                .orderBy(reviewLike.count().desc())
                .fetch();
    }
}
