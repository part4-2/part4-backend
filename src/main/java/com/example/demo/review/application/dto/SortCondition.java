package com.example.demo.review.application.dto;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;

@Getter
public enum SortCondition {
    POPULAR() {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return reviewLike.count().desc();
        }
    },
    RATING() {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return review.starRank.desc();
        }
    },
    RECENT() {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return review.createdDate.desc();
        }
    };


    public abstract OrderSpecifier<?> getSpecifier();

    SortCondition() {

    }
}
