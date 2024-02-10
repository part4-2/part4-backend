package com.example.demo.review.domain;

import com.example.demo.review.domain.vo.SearchCondition;
import com.example.demo.review_tag.domain.vo.TagName;

import java.util.List;

public interface ReviewQueryRepository {
    // 한방쿼리
    List<Review> findByTagNames(List<TagName> tagNames, SearchCondition searchCondition);

    List<Review> findByLikes();
}
