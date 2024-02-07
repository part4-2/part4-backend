package com.example.demo.review_tag.domain;

import com.example.demo.review.domain.ReviewQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, ReviewQueryRepository {
}
