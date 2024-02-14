package com.example.demo.spot.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.spot.application.SpotService;
import com.example.demo.spot.application.SpotStarService;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.dto.SpotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "Spot Controller", description = "여행지 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class SpotController {
    private final SpotService spotService;
    private final SpotStarService service;

    @PostMapping("/api/users/spots")
    @Operation(summary = "여행지 저장(DB)", description = "여행지 정보 저장.")
    public ResponseEntity<SpotResponse> write(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody SpotRequest spotRequest) {
        final SpotResponse response = service.saveOrGet(spotRequest);

//        final URI location = URI.create("/api/users/spots/" + savedId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/main/spots/{spot-id}")
    @Operation(summary = "여행지 정보 찾기", description = "여행지 id로 여행지 정보를 찾아옵니다.")
    public ResponseEntity<SpotResponse> getById(@PathVariable("spot-id") String spotId) {
        SpotResponse response = service.getInfoByPlaceId(spotId);
        return ResponseEntity.ok(response);
    }
}