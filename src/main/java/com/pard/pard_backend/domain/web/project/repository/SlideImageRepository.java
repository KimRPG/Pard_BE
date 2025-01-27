package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.SlideImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlideImageRepository extends JpaRepository<SlideImage, Long> {
    List<SlideImage> findByProjectIdOrderByOrderNumber(Long projectId);
}
