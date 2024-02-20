package com.example.demo.review.application.dto;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;

@Getter
public enum SortCondition {
    POPULAR() {
        @Override
        public OrderSpecifier<?>[] getSpecifier() {
            return new OrderSpecifier<?>[] { reviewLike.count().desc(), review.createdDate.desc() };
        }
    },
    RATING() {
        @Override
        public OrderSpecifier<?>[] getSpecifier() {
            return new OrderSpecifier<?>[] { review.starRank.desc(), review.createdDate.desc() };
        }
    },
    RECENT() {
        @Override
        public OrderSpecifier<?>[] getSpecifier() {
            return new OrderSpecifier<?>[] { review.createdDate.desc() };
        }
    };

    public abstract OrderSpecifier<?>[] getSpecifier();
}