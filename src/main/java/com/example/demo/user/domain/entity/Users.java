package com.example.demo.user.domain.entity;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickName;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public Users(String email, String nickName, String imageUrl, Gender gender, LocalDate birthDate, String oauthId, Role role) {
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.birthDate = birthDate;
        this.oauthId = oauthId;
        this.role = role;
    }

    public void updateUserInfo(UpdateUserRequest updateUserRequest) {
        this.email = updateUserRequest.getEmail();
        this.nickName = updateUserRequest.getNickName();
        this.gender = Gender.getInstance(updateUserRequest.getGender());
        this.birthDate = updateUserRequest.getBirthDate();
    }

    public void updateEssentials(RequiredUserInfoRequest requiredUserInfoRequest) {
        this.nickName = requiredUserInfoRequest.getNickName();
        this.gender = Gender.getInstance(requiredUserInfoRequest.getGender());
        this.birthDate = requiredUserInfoRequest.getBirthDate();
        this.role = Role.USER;
    }

    public void updateProfileImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
