package com.example.demo.review.domain;

import com.example.demo.review.application.dto.ReviewListDTO;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Companion;
import com.example.demo.review.domain.vo.PlaceType;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Weather;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;
import static com.example.demo.review_photo.domain.QReviewPhoto.*;

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

    private BooleanExpression findBySearchWord(String searchWord) {
        return searchWord == null ? null : review.title.value.like("%" + searchWord + "%").or(review.content.value.like("%" + searchWord + "%"));
    }

    private BooleanExpression findByWeather(Weather weather) {
        return weather == Weather.NONE ? null : review.tag.weather.eq(weather);
    }

    private BooleanExpression findByCompanion(Companion companion) {
        return companion == Companion.NONE ? null : review.tag.companion.eq(companion);
    }

    private BooleanExpression findByPlaceType(PlaceType placeType) {
        return placeType == PlaceType.NONE ? null : review.tag.placeType.eq(placeType);
    }

    private BooleanExpression findByTag(Weather weather, Companion companion, PlaceType placeType) {

        BooleanExpression weatherExpr = findByWeather(weather);
        BooleanExpression companionExpr = findByCompanion(companion);
        BooleanExpression placeTypeExpr = findByPlaceType( placeType);

        BooleanExpression finalExpr = Expressions.asBoolean(true).isTrue();

        if(weatherExpr != null) {
            finalExpr = finalExpr.and(weatherExpr);
        }
        if(companionExpr != null) {
            finalExpr = finalExpr.and(companionExpr);
        }
        if(placeTypeExpr != null) {
            finalExpr = finalExpr.and(placeTypeExpr);
        }

        return finalExpr;
    }
    @Override
    public List<ReviewListDTO> getListWithSearchCondition(String searchValue, Tag tag, SortCondition sortCondition) {
        return queryFactory.select(Projections.constructor(ReviewListDTO.class,
                        review.id,
                        review.title,
                        review.tag,
                        review.users.nickName,
                        review.visitingTime,
                        reviewPhoto.url,
                        review.starRank
                ))
                .from(review)
                .leftJoin(reviewLike)
                .on(review.id.eq(reviewLike.reviewId))
                .leftJoin(reviewPhoto)
                .on(reviewPhoto.review.id.eq(review.id))
                .groupBy(review.id)
                .where(findBySearchWord(searchValue),
                        findByTag(tag.getWeather(), tag.getCompanion(), tag.getPlaceType()))
                .orderBy(sortCondition.getSpecifier())
                .fetch();

    }
}