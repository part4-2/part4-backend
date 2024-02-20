package com.example.demo.oauth;

import com.example.demo.user.domain.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "소셜로그인 회원가입 유저",description = "소셜로그인을 통해 회원가입한 유저의 정보")
public class LoginResponse {

    @Schema(description = "유저 Id")
    private Long id;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "프로필 사진")
    private String imageUrl;
    private String role;
    private String tokenType;
    private String accessToken;


    public LoginResponse(Users users,String tokenType, String accessToken) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.imageUrl = users.getImageUrl();
        this.role = users.getRole().getName();
        this.tokenType = tokenType;
        this.accessToken = accessToken;

    }
}