package com.example.demo.star.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.spot.application.SpotStarService;
import com.example.demo.star.dto.StarRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "SpotStar", description = "여행지 별점 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class SpotStarController {
    private final SpotStarService service;

    @PostMapping("/api/spots/stars")
    @Operation(summary = "별점 저장", description = "별점을 저장합니다")
    public void rank(
            @RequestBody StarRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        service.rank(request, userDetails.getUsers());
    }
    @PutMapping("/api/spots/stars")
    @Operation(summary = "별점 수정", description = "별점을 수정합니다.")
    public void updateRank(
            @RequestBody StarRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        service.updateRank(request, userDetails.getUsers());
    }
}

