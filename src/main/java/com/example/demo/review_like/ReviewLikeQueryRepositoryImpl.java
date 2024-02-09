package com.example.demo.review_like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewLikeQueryRepositoryImpl implements ReviewLikeQueryRepository{
    private final JPAQueryFactory queryFactory;
}
