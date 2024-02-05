package com.example.demo.user.controller;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.UserInfoUpdateRequest;
import com.example.demo.user.domain.response.UpdateUserResponse;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserController" , description = "회원 기능 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @Operation(summary = "필수정보 입력" , description = "서비스 이용을 위한 필수정보를 입력합니다.")
    @PostMapping("/api/guest/update")
    public UpdateUserResponse updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody UserInfoUpdateRequest userInfoUpdateRequest) {
        Users users = userService.updateUser(customUserDetails, userInfoUpdateRequest);
        customUserDetails.getUsers().updateEssentials(userInfoUpdateRequest);

        return new UpdateUserResponse(users);
    }

    @GetMapping("/api/user/test")
    public String test() {
        return "oauth 회원가입 , 로그인 , 필수정보 입력 성공";
    }
}
