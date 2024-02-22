package com.example.demo.review.application.dto;

import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;

import java.time.LocalDateTime;

public record ReviewListDTO(Long reviewId,
                            Title title,
                            Tag tag,
                            String nickName,
                            LocalDateTime visitingTime,
                            StarRank stars,
                            String image) {

}