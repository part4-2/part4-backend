package com.example.demo.user.domain.response;

import com.example.demo.user.domain.entity.Users;

public record UserInfoResponse(
        Long userId,
        String nickName,
        String gender,
        Integer age,
        String imageUrl
) {
    public static UserInfoResponse of(Users user){
        return new UserInfoResponse(
                user.getId(),
                user.getNickName(),
                user.getGender().getName(),
                user.getAge(),
                user.getImageUrl()
        );
    }
}
