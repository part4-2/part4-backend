package com.example.demo.review.domain;

import com.example.demo.global.exception.DateTimeCustomException;
import com.example.demo.global.exception.SortException;
import com.example.demo.review.application.dto.ReviewListData;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Companion;
import com.example.demo.review.domain.vo.PlaceType;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.user.domain.entity.Users;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.example.demo.review.domain.QReview.review;
import static com.example.demo.review_like.domain.QReviewLike.reviewLike;
import static com.example.demo.review_photo.domain.QReviewPhoto.reviewPhoto;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private static final int MY_REVIEW_PAGE_SIZE = 6;
    private static final int PAGE_SIZE = 24;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewListData> findByLikes(SortCondition order) {
        return queryFactory.select(Projections.constructor(ReviewListData.class,
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

    private BooleanExpression findByMonth(Integer month) {
        if (month == null) {
            return null;
        }

        if (month < 1 || month > 12) {
            throw new DateTimeCustomException.InvalidFormatOfMonthException(month);
        }

        return review.visitingTime.month().eq(month);
    }

    private BooleanExpression findByHour(Integer hour) {
        if (hour == null) {
            return null;
        }

        if (hour < 1 || hour >= 24) {
            throw new DateTimeCustomException.InvalidFormatOfHourException(hour);
        }

        return review.visitingTime.hour().eq(hour);
    }

    private BooleanExpression findByTag(Weather weather, Companion companion, PlaceType placeType) {

        BooleanExpression weatherExpr = findByWeather(weather);
        BooleanExpression companionExpr = findByCompanion(companion);
        BooleanExpression placeTypeExpr = findByPlaceType(placeType);

        BooleanExpression finalExpr = Expressions.asBoolean(true).isTrue();

        if (weatherExpr != null) {
            finalExpr = finalExpr.and(weatherExpr);
        }
        if (companionExpr != null) {
            finalExpr = finalExpr.and(companionExpr);
        }
        if (placeTypeExpr != null) {
            finalExpr = finalExpr.and(placeTypeExpr);
        }

        return finalExpr;
    }

    @Override
    public List<ReviewListData> getListWithSearchCondition(String searchValue,
                                                           Tag tag,
                                                           SortCondition sortCondition,
                                                           Integer month,
                                                           Integer hour,
                                                           int page) {
        if (sortCondition == null) {
            throw new SortException.SortConditionNotFoundException();
        }

        return queryFactory.select(Projections.constructor(ReviewListData.class,
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
                .offset((long) (page - 1) * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();
    }

    @Override
    public List<ReviewListData> getMyReviews(Users users,
                                             int page,
                                             Tag tag,
                                             Integer month,
                                             int size) {
        return queryFactory.select(Projections.constructor(ReviewListData.class,
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
                .where(review.users.id.eq(users.getId())
                        .and(findByTag(tag.getWeather(), tag.getCompanion(), tag.getPlaceType()))
                        .and(findByMonth(month))
                )
                .orderBy(review.createdDate.desc())
                .offset((long) (page - 1) * size)
                .limit(size)
                .fetch();
    }

    @Override
    public Set<String> getMyPlacedIds(Users users) {
        List<String> placedIds = queryFactory.select(review.spot.placeId)
                .from(review)
                .where(review.users.id.eq(users.getId()))
                .fetch();

        return Set.copyOf(placedIds);
    }
}