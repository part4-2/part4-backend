package com.example.demo.user.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Schema(name = "필수정보 입력 요청")
public class RequiredUserInfoRequest {
    @Schema(description = "생일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "닉네임")
    private String nickName;
}
