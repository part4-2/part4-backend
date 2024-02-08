package com.example.demo.review_like;

import com.example.demo.review_like.domain.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long>, ReviewLikeQueryRepository {
}
