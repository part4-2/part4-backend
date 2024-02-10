package com.example.demo.review.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.review.application.ReviewService;
import com.example.demo.review.application.dto.ReviewRequest;
import com.example.demo.review.application.dto.ReviewResponseDTO;
import com.example.demo.review.application.dto.ReviewWriteRequest;
import com.example.demo.review.domain.vo.SearchCondition;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.review_tag.domain.vo.TagName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Review Controller", description = "리뷰 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/user/spots/{spotId}/reviews")
    @Operation(summary = "리뷰 쓰기", description = "리뷰를 작성합니다.")
    public ResponseEntity<Void> writeReview(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid ReviewWriteRequest reviewWriteRequest,
            @RequestParam Weather weather,
            @PathVariable Long spotId
    ) {
        final Long id = reviewService.write(
                new ReviewRequest(reviewWriteRequest.title(),
                        reviewWriteRequest.content()),
                customUserDetails.getUserEmail(),
                spotId,
                weather
        );

        final URI location = URI.create("/api/spot/" + spotId + "/reviews" + id);

        return ResponseEntity.created(location).build();
    }

    // TODO: 2/7/24 spotId 빼도 될수도?
    @GetMapping("/api/user/spots/{spot-id}/reviews/{review-id}")
    @Operation(summary = "리뷰 조회", description = "리뷰 id에 해당하는 리뷰 정보를 불러옵니다.")
    public ResponseEntity<ReviewResponseDTO> getReview(@PathVariable("spot-id") Long spotId,
                                                       @PathVariable("review-id") Long reviewId) {
        final ReviewResponseDTO result = reviewService.getOneById(reviewId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/user/spots/{spot-id}/reviews/{review-id}")
    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다. 수정 시 모든 컬럼의 정보가 필요합니다.")
    public ResponseEntity<Void> updateReview(@PathVariable("spot-id") Long spotId,
                                             @RequestBody @Valid ReviewWriteRequest reviewRequest,
                                             @RequestParam Weather weather,
                                             @PathVariable("review-id") Long reviewId) {
        reviewService.updateReview(reviewId, reviewRequest, weather);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user/spots/{spot-id}/reviews")
    @Operation(summary = "리뷰 조회(리스트)", description = "태그, 조회 조건(좋아요, 최신순)에 따라 리뷰들을 조회합니다.")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(
            @PathVariable("spot-id") Long spotId,
            @RequestParam List<String> tagNames,
            @RequestParam SearchCondition searchCondition
    ) {
        List<TagName> tags = new ArrayList<>();
        if (!tagNames.isEmpty()) {
            tags = tagNames.stream().map(TagName::new).toList();
        }

        List<ReviewResponseDTO> result = reviewService.findByTagNames(tags, searchCondition);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/user/main/reviews")
    @Operation(summary = "리뷰 조회(리스트)", description = "좋아요를 많이 받은 순으로 20개의 리뷰를 조회합니다.")
    public ResponseEntity<List<ReviewResponseDTO>> get20ReviewsByLikes(){
        List<ReviewResponseDTO> result = reviewService.findByLikes();

        if (result == null || result.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }
}