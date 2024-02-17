package com.example.demo.review.application.dto;

import com.example.demo.review.exception.ReviewException;
import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

import java.util.Arrays;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;

@Getter
public enum SortCondition {
    LIKES("likes") {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return reviewLike.count().desc();
        }
    },
    STARS("stars") {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return review.starRank.desc();
        }
    },
    NEW("new") {
        @Override
        public OrderSpecifier<?> getSpecifier() {
            return review.createdDate.desc();
        }
    };

    private final String description;

    public abstract OrderSpecifier<?> getSpecifier();

    public static SortCondition getInstance(String value){
        return Arrays.stream(values())
                .filter(sortCondition -> sortCondition.description.equals(value))
                .findAny()
                .orElseThrow( () -> new ReviewException.SortConditionNotFoundException(value));
    }

    SortCondition(String description) {
        this.description = description;
    }
}
