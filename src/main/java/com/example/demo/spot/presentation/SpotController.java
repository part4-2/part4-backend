package com.example.demo.spot.presentation;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.spot.application.SpotService;
import com.example.demo.spot.domain.Spot;
import com.example.demo.spot.dto.SpotRequest;
import com.example.demo.spot.dto.SpotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "여행지 컨트롤러" , description = "여행지 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class SpotController {
    private final SpotService spotService;

    @PostMapping("/api/spots")
    @Operation(summary = "여행지 저장(DB)" , description = "여행지를 입력합니다. (프론트 측에서 find api 호출 후 없을 시 분기 태워서 호출해주세요)")
    public ResponseEntity<Void> write(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody SpotRequest spotRequest){
        final Long savedId = spotService.save(spotRequest);

        final URI location = URI.create("/api/spots/" + savedId);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/spots/{spot-id}")
    @Operation(summary = "여행지 정보 찾기" , description = "여행지 id로 여행지 정보를 찾아옵니다.")
    public ResponseEntity<SpotResponse> getById(@PathVariable("spot-id") Long spotId){
        Spot spot = spotService.findById(spotId);
        return ResponseEntity.ok(SpotResponse.of(spot));
    }

    @GetMapping("/api/spots")
    @Operation(summary = "여행지 정보 찾기" , description = "여행지 주소이름으로(formattedAddress) 여행지 정보를 찾아옵니다. 이 API 이후 없으면 POST 요청 해주세요")
    public ResponseEntity<SpotResponse> getByAddress(@RequestParam String formattedAddress){
        return ResponseEntity.ok(spotService.findByAddress(formattedAddress));
    }
}