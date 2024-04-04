package com.example.demo.user.user.service;

import com.example.demo.common.service.ServiceTest;
import com.example.demo.common.test_instance.UserFixture;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserServiceTest extends ServiceTest {
    @Autowired
    private UserService userService;

    @Nested
    @DisplayName("닉네임 찾기 테스트")
    class findByNickNameTest {

        @Test
        @DisplayName("닉네임에 해당하는 유저가 있다면 조회에 성공한다.")
        void success_findNickName() {
            // given
            Users testUser = entityProvider.saveUser(UserFixture.FOO);

            // when
            Users users = userService.findByNickName(testUser.getNickName());

            // then
            assertThat(users).isEqualTo(testUser);
        }

        @Test
        @DisplayName("닉네임이 해당하는 유저가 없다면 예외처리를 진행한다.")
        void fail_findNickName() {
            // then
            assertThatThrownBy(() -> userService.findByNickName("failTest"))
                    .isInstanceOf(UserException.UserNotFoundException.class);
        }

    }
}