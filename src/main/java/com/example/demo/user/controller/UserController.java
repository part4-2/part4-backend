package com.example.demo.user.controller;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.s3upload.FileDto;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import com.example.demo.user.domain.response.UpdateUserResponse;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserController" , description = "회원 기능 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @Operation(summary = "필수정보 입력" , description = "서비스 이용을 위한 필수정보를 입력합니다.")
    @PostMapping("/api/guest/update")
    public UpdateUserResponse requiredUserInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody RequiredUserInfoRequest requiredUserInfoRequest) {
        return userService.requiredUserInfo(customUserDetails, requiredUserInfoRequest);
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보(이메일 , 닉네임 , 프로필사진 , 나이)를 수정합니다.")
    @PostMapping("/api/user/update")
    public UpdateUserResponse updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(customUserDetails, updateUserRequest);
    }

    @Operation(summary = "닉네임 중복 체크", description = "중복된 닉네임이 있는지 확인합니다.")
    @PostMapping("/api/nickname")
    public boolean nickNameIsDuplicated(@RequestParam String nickName) {
        return userService.checkNickName(nickName);
    }

    @Operation(summary = "프로필 사진 업로드", description = "유저의 프로필 사진을 업로드 합니다.")
    @PostMapping("/api/user/profileImage")
    public String uploadUserProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails, FileDto fileDto) {
        return userService.uploadUserProfile(customUserDetails,fileDto);
    }
}
