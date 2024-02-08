package com.example.demo.user.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "필수정보 입력 요청")
public class RequiredUserInfoRequest {
    @Schema(description = "나이")
    private int age;
    @Schema(description = "프로필 이미지")
    private String imageUrl;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "닉네임")
    private String nickName;
}
