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

    List<ReviewListData> getMyReviews(Users users,
                                      int page,
                                      Tag tag,
                                      Integer month);

    Set<String> getMyPlacedIds(Users users);
}
