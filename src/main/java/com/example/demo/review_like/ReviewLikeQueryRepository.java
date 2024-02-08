package com.example.demo.review_like;

import java.util.List;

public interface ReviewLikeQueryRepository {
    List<Long> getReviewByLikesDesc();
}
