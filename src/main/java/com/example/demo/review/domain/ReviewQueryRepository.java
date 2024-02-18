package com.example.demo.review.domain;

import com.example.demo.review.application.dto.ReviewListDTO;
import com.example.demo.review.application.dto.SortCondition;
import com.example.demo.review.domain.vo.Tag;

import java.util.List;

public interface ReviewQueryRepository {

    List<Review> findByLikes(SortCondition order);

    List<ReviewListDTO> getListWithSearchCondition(String searchValue, Tag tag, SortCondition sortCondition);
}
