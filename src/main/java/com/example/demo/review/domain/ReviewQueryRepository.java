package com.example.demo.review.domain;

import com.example.demo.review.application.dto.ReviewListData;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Tag;

import java.util.List;

public interface ReviewQueryRepository {

    List<ReviewListData> findByLikes(SortCondition order);


    List<ReviewListData> getListWithSearchCondition(String searchValue,
                                                    Tag tag,
                                                    SortCondition sortCondition,
                                                    Integer month,
                                                    Integer hour);
}
