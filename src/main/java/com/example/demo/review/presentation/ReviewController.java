package com.example.demo.review.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.review.application.ReviewService;
import com.example.demo.review.application.dto.ReviewRequest;
import com.example.demo.review.application.dto.ReviewResponseDTO;
import com.example.demo.review.application.dto.ReviewWriteRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/spots/{spotId}/reviews")
    @Operation(summary = "리뷰 쓰기" , description = "리뷰를 작성합니다.")
    public ResponseEntity<Void> writeReview(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid ReviewWriteRequest reviewWriteRequest,
            @PathVariable Long spotId
            ){
        final Long id = reviewService.write(
                new ReviewRequest(reviewWriteRequest.title(),
                        reviewWriteRequest.content()),
                customUserDetails.getUserEmail(),
                spotId,
                reviewWriteRequest.weatherDescription()
        );

        final URI location = URI.create("/api/spot/" + spotId + "/reviews" + id);

        return ResponseEntity.created(location).build();
    }

    // TODO: 2/7/24 spotId 빼도 될수도?
    @GetMapping("/api/spots/{spot-id}/reviews")
    @Operation(summary = "리뷰 조회" , description = "리뷰 id에 해당하는 리뷰 정보를 불러옵니다.")
    public ResponseEntity<ReviewResponseDTO> getReview(@PathVariable("spot-id") Long spotId,
                                                       @RequestParam("review-id") Long reviewId){
        final ReviewResponseDTO oneById = reviewService.getOneById(reviewId);
        return ResponseEntity.ok(oneById);
    }

    @PutMapping("/api/spots/{spot-id}/reviews/{review-id}")
    @Operation(summary = "리뷰 수정" , description = "리뷰를 수정합니다. 수정 시 모든 컬럼의 정보가 필요합니다.")
    public ResponseEntity<Void> updateReview(@PathVariable("spot-id") Long spotId,
                                                       @RequestBody @Valid ReviewWriteRequest reviewRequest,
                                                       @PathVariable("review-id") Long reviewId){
        reviewService.updateReview(reviewId,reviewRequest);
        return ResponseEntity.ok().build();
    }
}
