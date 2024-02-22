package com.example.demo.review_like.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.review.domain.vo.ReviewId;
import com.example.demo.review_like.application.ReviewLikeService;
import com.example.demo.user.domain.entity.vo.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ReviewLike Controller", description = "리뷰 좋아요 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    @PostMapping("/api/user/reviews/like/{review-id}")
    @Operation(summary = "리뷰 좋아요", description = "리뷰에 좋아요를 등록합니다")
    public ResponseEntity<Void> likeReview(
            @PathVariable(value = "review-id") Long reviewIdValue,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        final UserId userId = new UserId(userDetails.getUsers().getId());
        final ReviewId reviewId = new ReviewId(reviewIdValue);

        reviewLikeService.like(userId, reviewId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/user/reviews/like/{review-id}")
    @Operation(summary = "리뷰 좋아요 삭제", description = "리뷰의 좋아요를 삭제합니다")
    public ResponseEntity<Void> unlikeReview(
            @PathVariable(value = "review-id") Long reviewIdValue,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        final UserId userId = new UserId(userDetails.getUsers().getId());
        final ReviewId reviewId = new ReviewId(reviewIdValue);

        reviewLikeService.unlike(userId, reviewId);

        return ResponseEntity.ok().build();
    }
}