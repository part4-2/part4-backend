package com.example.demo.s3upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDto {
    private String url;
    private MultipartFile file;

    public FileEntity toEntity() {
        return new FileEntity(url);
    }
}
