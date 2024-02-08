package com.example.demo.user.user.service;

import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.s3upload.FileDto;
import com.example.demo.s3upload.FileService;
import com.example.demo.s3upload.S3Service;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.domain.request.RequiredUserInfoRequest;
import com.example.demo.user.domain.request.UpdateUserRequest;
import com.example.demo.user.domain.response.UpdateUserResponse;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final FileService fileService;

    public Users findByNickName(String nickName){
        return userRepository.findByNickName(nickName)
                .orElseThrow(
                        () -> new UserException.UserNotFoundException(nickName)
                );
    }

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

    public String uploadUserProfile(FileDto fileDto) {
        try {
            String url = s3Service.uploadFile(fileDto.getFile());
            fileDto.setUrl(url);
            fileService.save(fileDto);
            return url;
        } catch (IOException e) {
            throw new IllegalStateException("사진 업로드에 실패했습니다.");
        }
    }
}
