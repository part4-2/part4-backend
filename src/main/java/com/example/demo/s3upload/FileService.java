package com.example.demo.s3upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(FileDto fileDto) {
        fileRepository.save(fileDto.toEntity());
    }

    public List<FileEntity> getFiles() {
        return fileRepository.findAll();

    }
}
