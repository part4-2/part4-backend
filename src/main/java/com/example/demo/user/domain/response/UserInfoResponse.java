package com.example.demo.user.domain.response;

import com.example.demo.user.domain.entity.Users;

import java.time.LocalDate;

public record UserInfoResponse(
        Long userId,
        String nickName,
        String gender,
        Integer age,
        LocalDate birthdate,
        String imageUrl
) {
    public static UserInfoResponse of(Users user){
        return new UserInfoResponse(
                user.getId(),
                user.getNickName(),
                user.getGender().getName(),
                user.getAge(),
                user.getBirthDate(),
                user.getImageUrl()
        );
    }
}
