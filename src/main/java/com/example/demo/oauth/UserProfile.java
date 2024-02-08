package com.example.demo.oauth;

import com.example.demo.user.domain.entity.Role;
import com.example.demo.user.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;
    private final String email;
    private final String name;
    private final String imageUrl;

    @Builder
    public UserProfile(String oauthId, String email, String name, String imageUrl) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .nickName(name)
                .oauthId(oauthId)
                .role(Role.GUEST)
                .imageUrl(imageUrl)
                .build();

    }
}