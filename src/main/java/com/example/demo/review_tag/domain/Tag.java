package com.example.demo.review_tag.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.Review;
import com.example.demo.review_tag.domain.vo.TagName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TagName name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    public Tag(TagName name, Review review) {
        this.name = name;
        this.review = review;
    }
}
