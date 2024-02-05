package com.example.demo.user.service;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.UserInfoUpdateRequest;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Users updateUser(CustomUserDetails customUserDetails, UserInfoUpdateRequest userInfoUpdateRequest) {
        Users users = customUserDetails.getUsers();
        users.updateEssentials(userInfoUpdateRequest);
        userRepository.save(users);

        return users;
    }
}
