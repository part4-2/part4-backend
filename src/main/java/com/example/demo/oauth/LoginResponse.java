package com.example.demo.oauth;

import com.example.demo.user.domain.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String nickName;
    private String email;
    private String imageUrl;
    private Role role;
    private String tokenType;
    private String accessToken;


    @Builder
    public LoginResponse(Long id, String nickName, String email, String imageUrl, Role role, String tokenType, String accessToken) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.tokenType = tokenType;
        this.accessToken = accessToken;

    }
}
