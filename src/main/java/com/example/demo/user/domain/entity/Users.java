package com.example.demo.user.domain.entity;

import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.enums.GenderConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickName;

    @Convert(converter = GenderConverter.class)
    private Gender gender;

    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public Users(String email, String nickName, Gender gender, int age, Provider provider) {
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.age = age;
        this.provider = provider;
        this.role = Role.USER;
    }

    public Users update(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
