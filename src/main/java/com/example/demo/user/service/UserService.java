package com.example.demo.user.service;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.s3upload.S3Service;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import com.example.demo.user.domain.response.UpdateUserResponse;
import com.example.demo.user.domain.response.UserInfoResponse;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final S3Service s3Service;

    public Users findByNickName(String nickName){
        return userRepository.findByNickName(nickName)
                .orElseThrow(
                        () -> new UserException.UserNotFoundException(nickName)
                );
    }

    public UserInfoResponse getUserInfo(CustomUserDetails customUserDetails){
        Users user = customUserDetails.getUsers();

        return UserInfoResponse.of(user);
    }

    @Transactional
    public UpdateUserResponse completeSignup(CustomUserDetails customUserDetails, RequiredUserInfoRequest requiredUserInfoRequest) {
        if (checkNickName(requiredUserInfoRequest.getNickName())) {
            Users users = customUserDetails.getUsers();
            users.updateEssentials(requiredUserInfoRequest);

            userRepository.save(users);

            return new UpdateUserResponse(users);
        } else {
            throw new IllegalArgumentException("중복된 닉네임은 사용할수 없습니다.");
        }
    }

    public UpdateUserResponse updateUserInfo(CustomUserDetails customUserDetails, UpdateUserRequest updateUserRequest) {
        if (checkNickName(updateUserRequest.getNickName())) {
            Users users = customUserDetails.getUsers();
            users.updateUserInfo(updateUserRequest);

            userRepository.save(users);
            return new UpdateUserResponse(users);
        } else {
            throw new IllegalArgumentException("중복된 닉네임은 사용할수 없습니다.");
        }
    }

    public boolean checkNickName(String nickName) {
        if (nickName == null) {
            return true;
        }
        return userRepository.findByNickName(nickName).isEmpty();
    }

    @Transactional
    public String uploadUserProfile(CustomUserDetails customUserDetails, MultipartFile profileImage) {
        String url = s3Service.uploadFile(profileImage);
        Users users = customUserDetails.getUsers().updateProfileImage(url);

        userRepository.save(users);

        return url;
    }
}

