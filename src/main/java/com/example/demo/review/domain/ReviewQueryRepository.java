package com.example.demo.review.domain;

import com.example.demo.review.application.dto.ReviewListData;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.user.domain.entity.Users;

import java.util.List;
import java.util.Set;

public interface ReviewQueryRepository {

    List<ReviewListData> findByLikes(SortCondition order);


    List<ReviewListData> getListWithSearchCondition(String searchValue,
                                                    Tag tag,
                                                    SortCondition sortCondition,
                                                    Integer month,
                                                    Integer hour,
                                                    int page);

    long searchConditionTotal(String searchValue, Tag tag, Integer month, Integer hour);

    List<ReviewListData> getMyReviews(Users users,
                                      int page,
                                      Tag tag,
                                      Integer month,
                                      int size);

    long getMyReviewsTotalCount(Users users, Tag tag, Integer month);

    Set<String> getMyPlacedIds(Users users);
}
