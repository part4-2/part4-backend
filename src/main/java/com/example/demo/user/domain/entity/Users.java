package com.example.demo.user.domain.entity;

import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.request.UserInfoUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Users extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickName;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Users(String email, String nickName, String imageUrl, Gender gender, int age, String oauthId, Role role) {
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.age = age;
        this.oauthId = oauthId;
        this.role = role;
    }

    public Users update(String name, String email, String imageUrl) {
        this.nickName = name;
        this.email = email;
        this.imageUrl = imageUrl;
        return this;
    }

    public Users updateEssentials(UserInfoUpdateRequest userInfoUpdateRequest) {
        this.nickName = userInfoUpdateRequest.getNickName();
        this.imageUrl = userInfoUpdateRequest.getImageUrl();
        this.gender = Gender.getInstance(userInfoUpdateRequest.getGender());
        this.age = userInfoUpdateRequest.getAge();
        this.role = Role.USER;

        return this;
    }

}
