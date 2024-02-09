package com.example.demo.review.domain;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import jakarta.persistence.*;
import lombok.*;

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
    private Weather weather;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;
    @Builder
    public Review(Title title, Content content, Weather weather, Users users, Spot spot) {
        this.title = title;
        this.content = content;
        this.weather = weather;
        this.users = users;
        this.spot = spot;
    }

    public void update(final Weather weather,
                       final Title title,
                       final Content content){
        this.weather = weather;
        this.title = title;
        this.content = content;
    }
}