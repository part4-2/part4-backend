package com.example.demo.review_photo.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewPhoto extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String url;
    @ManyToOne
    private Review review;
    public ReviewPhoto(String url) {
        this.url = url;
    }
}
