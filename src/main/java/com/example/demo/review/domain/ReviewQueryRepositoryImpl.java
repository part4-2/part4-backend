package com.example.demo.review.domain;

import com.example.demo.review.domain.vo.SearchCondition;
import com.example.demo.review_tag.domain.vo.TagName;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    // TODO: 2/9/24 이 쿼리 leftJoin 처리하면 결과가 나올 것임. 그러나 태그 엔티티 작업 후 테스트하기로...
    @Override
    public List<Review> findByTagNames(List<TagName> tags, SearchCondition searchCondition){
        List<Long> taggedReviews = this.findTaggedReviews(tags);
        return queryFactory.selectFrom(review)
                .innerJoin(reviewTag)
                .on(review.id.eq(reviewTag.review.id))
                .where(searchByTagNamesIfPresent(taggedReviews))
                .innerJoin(reviewLike)
                .on(reviewLike.review.id.eq(review.id))
                .groupBy(review)
                .orderBy(this.getOrder(searchCondition))
                .fetch();
    }

    @Override
    public List<Review> findByLikes() {
        return queryFactory.selectFrom(review)
                .leftJoin(review.reviewLikes, reviewLike)
                .groupBy(review.id) // 리뷰의 id로 그룹화하여
                .orderBy(reviewLike.count().desc(), review.createdDate.desc()) // reviewLike의 개수로 내림차순 정렬
                .limit(20) // 상위 20개 리뷰만 선택
                .fetch();
    }

    private List<Long> findTaggedReviews(List<TagName> tags){
        return queryFactory.select(reviewTag.id)
                .from(reviewTag)
                .innerJoin(tag)
                .on(tag.id.eq(reviewTag.tag.id))
                .where(searchByTagNames(tags))
                .fetch();
    }

    private BooleanExpression searchByTagNamesIfPresent(List<Long> taggedReviews){
        return taggedReviews == null || taggedReviews.isEmpty() ? null : reviewTag.id.in(taggedReviews);
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