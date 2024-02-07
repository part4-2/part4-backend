package com.example.demo.review.domain;

import com.example.demo.review.domain.vo.SearchCondition;
import com.example.demo.review_tag.domain.vo.TagName;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;
import static com.example.demo.review_tag.domain.QReviewTag.reviewTag;
import static com.example.demo.review_tag.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;
    // 한방쿼리

    @Override
    @Transactional(readOnly = true)
    public List<Review> findByTagNames(List<TagName> tagNames, SearchCondition searchCondition) {
        return queryFactory
                .select(review)
                .distinct()
                .from(review)
                .innerJoin(review, reviewTag.review)
                .innerJoin(reviewTag.tag, tag)
                .where(
                        this.searchByTagNames(tagNames)
                        )
                .groupBy(review)
                .orderBy(this.getOrder(searchCondition))
                .fetch();
    }
    private BooleanExpression searchByTagNames(List<TagName> tagNames){
        return tagNames == null || tagNames.isEmpty() ? null : tag.name.in(tagNames);
    }
    private OrderSpecifier<Long> orderByLike(){
        return reviewLike.count().desc();
    }

    private OrderSpecifier<LocalDateTime> orderByNew(){
        return review.modifiedDate.desc();
    }

    private OrderSpecifier<?> getOrder(SearchCondition searchCondition) {
        if (searchCondition == SearchCondition.NEW){
            return orderByNew();
        }
        // 디폴트로 라이크순
        return this.orderByLike();
    }
}