package com.example.demo.common.test_instance;

import com.example.demo.user.domain.entity.Role;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.enums.Gender;

import java.time.LocalDate;

public class UserFixture {

    public static final Users FOO = new Users("FOO",
            "FOO",
            "FOO",
            Gender.MALE,
            LocalDate.now(),
            "FOO",
            Role.USER
    );
    public static final Users DK_USER = new Users("laancer4@gmail.com",
            "DK",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Role.USER
    );

    public static final Users DK_ADMIN = new Users("laancer4@gmail.com",
            "DK_ADMIN",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Role.USER
    );
}