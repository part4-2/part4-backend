package com.example.demo.review.domain;

import java.util.List;

public interface ReviewQueryRepository {

    List<Review> findByLikes();
}
