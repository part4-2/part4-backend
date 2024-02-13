package com.example.demo.review.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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

    @Builder
    public Review(Title title, Content content, Tag tag, Users users, Spot spot, LocalDateTime visitingTime) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.users = users;
        this.spot = spot;
        this.visitingTime = visitingTime;
    }

    public void update(final Tag tag,
                       final Title title,
                       final Content content,
                       final LocalDateTime visitingTime){
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.visitingTime = visitingTime;
    }
}