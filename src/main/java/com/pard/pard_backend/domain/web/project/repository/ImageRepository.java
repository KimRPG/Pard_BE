package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProjectIdAndApiType(Long projectId, String apiType);
    Optional<Image> findByProjectIdAndImageTypeAndApiType(Long projectId, String imageType, String apiType);  // 프로젝트의 특정 타입 이미지 조회
}
