package com.example.demo.user.domain.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String nickName;
    private String imageUrl;
    private int age;
    private String gender;
}
