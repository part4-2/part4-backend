package com.example.demo.review.application.dto;

import com.example.demo.review.domain.vo.StarRank;
import com.example.demo.review.domain.vo.Tag;
import com.example.demo.review.domain.vo.Title;

import java.time.LocalDateTime;

public record ReviewListDTO(Long id, Title title, Tag tag, String nickName, LocalDateTime visitingTime, String url,
                            StarRank starRank) {

}