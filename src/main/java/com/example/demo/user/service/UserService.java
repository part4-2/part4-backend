package com.example.demo.user.service;

import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.UpdateUserRequest;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void updateUser(UpdateUserRequest userRequest) {
        Users users = userRepository.findById(userRequest.getId()).orElseThrow(() -> new IllegalArgumentException(userRequest.getEmail() + " 회원을 찾을수 없습니다."));
        users.update(userRequest.getNickName(), userRequest.getEmail(), userRequest.getImageUrl());
    }
}
