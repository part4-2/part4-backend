package com.example.demo.review_photo.domain;

import com.example.demo.review.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPhoto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
    // TODO: 1/31/24 url 스펙 정해지면 객체로 바꿀 것
    private String url;
}
