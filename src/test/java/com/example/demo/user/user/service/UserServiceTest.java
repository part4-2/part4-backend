package com.example.demo.user.user.service;

import com.example.demo.common.service.ServiceTest;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.enums.Gender;
import com.example.demo.user.domain.enums.Role;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends ServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Users user = Users.builder()
                .nickName("test")
                .imageUrl("testUrl")
                .gender(Gender.MALE)
                .role(Role.GUEST)
                .build();

        userRepository.save(user);
    }

    @Nested
    @DisplayName("닉네임 찾기 테스트")
    class findByNickNameTest {

        @Test
        @DisplayName("닉네임이 존재한다면 닉네임 찾기에 성공한다.")
        void success_findNickName() {
            // when
            Optional<Users> test = userRepository.findByNickName("test");

            // then
            assertThat(test).isNotEmpty();
        }

        @Test
        @DisplayName("닉네임이 존재하지 않는다면 예외처리를 진행한다.")
        void fail_findNickName() {
            // then
            assertThrows(UserException.class, () -> {
                userService.findByNickName("failTest");
            });
        }
    }
}