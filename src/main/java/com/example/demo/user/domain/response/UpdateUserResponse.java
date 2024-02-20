package com.example.demo.user.domain.response;

import com.example.demo.user.domain.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "필수정보 입력된 회원 정보")
public class UpdateUserResponse {
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "프로필 사진")
    private String imageUrl;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "나이")
    private int age;
    @Schema(description = "권한")
    private String role;

    public UpdateUserResponse(Users users) {
        this.email = users.getEmail();
        this.nickName = users.getNickName();
        this.imageUrl = users.getImageUrl();
        this.gender = users.getGender().getName();
        this.age = users.getAge();
        this.role = users.getRole().getName();
    }
}
