package com.example.demo.review_like.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.Review;
import com.example.demo.user.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    public ReviewLike(Review review, Users users) {
        this.review = review;
        this.users = users;
    }
}