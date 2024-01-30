package com.example.demo.review.domain;

import com.example.demo.global.domain.TimeStamp;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.Weather;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
public class Review extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Content content;
    private LocalDateTime dateTime;
    private Weather weather;
}