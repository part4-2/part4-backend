package com.example.demo.common.test_instance;

import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.enums.Provider;
import com.example.demo.user.domain.enums.Role;

import java.time.LocalDate;

public class UserFixture {

    public static final Users FOO = new Users("FOO",
            "FOO",
            "FOO",
            Gender.MALE,
            LocalDate.now(),
            "FOO",
            Provider.GOOGLE,
            Role.USER,
            10
    );
    public static final Users DK_USER = new Users("laancer4@gmail.com",
            "DK",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Provider.GOOGLE,
            Role.USER,
            10
    );

    public static final Users DK_ADMIN = new Users("laancer4@gmail.com",
            "DK_ADMIN",
            "imageUrl",
            Gender.MALE,
            LocalDate.now(),
            "authId",
            Provider.GOOGLE,
            Role.USER,
            10
    );
}