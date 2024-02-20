package com.example.demo.review.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review_photo.domain.ReviewPhoto;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Title title;
    @Embedded
    private Content content;
    private LocalDateTime visitingTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;
    @Embedded
    private Tag tag;
    private StarRank starRank;
    @OneToMany
    @JoinColumn(name = "review_id")
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

    @Builder
    public Review(Title title, Content content, Tag tag, Users users, Spot spot, LocalDateTime visitingTime, StarRank starRank) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.users = users;
        this.spot = spot;
        this.visitingTime = visitingTime;
        this.starRank = starRank;
    }

    public void update(final Tag tag,
                       final Title title,
                       final Content content,
                       final LocalDateTime visitingTime,
                       final StarRank starRank,
                       final Spot spot){
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.visitingTime = visitingTime;
        this.starRank = starRank;
        this.spot = spot;
    }
}