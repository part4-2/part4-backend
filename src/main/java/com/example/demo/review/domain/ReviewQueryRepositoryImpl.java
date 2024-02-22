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
import static com.example.demo.review_photo.domain.QReviewPhoto.reviewPhoto;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewListDTO> findByLikes(SortCondition order) {
        return queryFactory.select(Projections.constructor(ReviewListDTO.class,
                        review.id,
                        review.title,
                        review.tag,
                        review.users.nickName,
                        review.visitingTime,
                        review.starRank,
                        reviewPhoto.url
                ))
                .from(review)
                .leftJoin(reviewLike)
                .on(review.id.eq(reviewLike.reviewId))
                .leftJoin(reviewPhoto)
                .on(reviewPhoto.review.id.eq(review.id))
                .groupBy(review.id)
                .orderBy(order.getSpecifier())
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

    private BooleanExpression findByMonth(Integer month){
        return month == null ? null : review.visitingTime.month().eq(month);
    }

    private BooleanExpression findByHour(Integer hour){
        return hour == null ? null : review.visitingTime.hour().eq(hour);
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
    public List<ReviewListDTO> getListWithSearchCondition(String searchValue,
                                                          Tag tag,
                                                          SortCondition sortCondition,
                                                          Integer month,
                                                          Integer hour) {
        return queryFactory.select(Projections.constructor(ReviewListDTO.class,
                        review.id,
                        review.title,
                        review.tag,
                        review.users.nickName,
                        review.visitingTime,
                        review.starRank,
                        reviewPhoto.url
                ))
                .from(review)
                .leftJoin(reviewLike)
                .on(review.id.eq(reviewLike.reviewId))
                .leftJoin(reviewPhoto)
                .on(reviewPhoto.review.id.eq(review.id))
                .groupBy(review.id)
                .where(findBySearchWord(searchValue),
                        findByTag(tag.getWeather(), tag.getCompanion(), tag.getPlaceType()),
                        findByHour(hour),
                        findByMonth(month))
                .orderBy(sortCondition.getSpecifier())
                .fetch();

    }
}