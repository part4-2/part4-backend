package com.example.demo.user.service;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import com.example.demo.user.domain.response.UpdateUserResponse;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UpdateUserResponse requiredUserInfo(CustomUserDetails customUserDetails, RequiredUserInfoRequest requiredUserInfoRequest) {
        Users users = customUserDetails.getUsers();
        users.updateEssentials(requiredUserInfoRequest);

        userRepository.save(users);

        return new UpdateUserResponse(users);
    }

    public UpdateUserResponse updateUser(CustomUserDetails customUserDetails, UpdateUserRequest updateUserRequest) {
        Users users = customUserDetails.getUsers();
        users.updateUserInfo(updateUserRequest);

        userRepository.save(users);

        return new UpdateUserResponse(users);
    }

    public boolean checkNickName(String nickName) {
        return userRepository.findByNickName(nickName).isEmpty();
    }
}
