package com.example.demo.review_photo.repository;

import com.example.demo.review_photo.domain.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto,Long> {
}
