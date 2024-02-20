package com.example.demo.user.domain.entity;

import com.example.demo.global.domain.BaseTimeEntity;
import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.enums.Provider;
import com.example.demo.user.domain.enums.Role;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

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

    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private Integer age;

    @Builder
    public Users(String email, String nickName, String imageUrl, Gender gender, LocalDate birthDate, String oauthId, Provider provider, Role role) {
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.age = calculateAge(birthDate);
        this.oauthId = oauthId;
        this.provider = provider;
        this.role = role;
    }

    public void updateUserInfo(UpdateUserRequest updateUserRequest) {
        this.email = updateUserRequest.getEmail();
        this.nickName = updateUserRequest.getNickName();
        this.gender = Gender.getInstance(updateUserRequest.getGender());
        this.age = calculateAge(updateUserRequest.getBirthDate());

    }

    public void updateEssentials(RequiredUserInfoRequest requiredUserInfoRequest) {
        this.nickName = requiredUserInfoRequest.getNickName();
        this.gender = Gender.getInstance(requiredUserInfoRequest.getGender());
        this.age = calculateAge(requiredUserInfoRequest.getBirthDate());
        this.role = Role.USER;
    }

    public Users updateProfileImage(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer calculateAge(LocalDate birthDate) {

        if (birthDate == null) {
            return null;
        }

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }


}

