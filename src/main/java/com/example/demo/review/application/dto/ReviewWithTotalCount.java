package com.example.demo.review.application.dto;

import java.util.List;

public record ReviewWithTotalCount(List<ReviewListDTO> reviewList, long totalCount) {
}
