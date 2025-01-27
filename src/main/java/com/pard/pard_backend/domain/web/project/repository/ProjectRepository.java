package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllByOrderByOrderNumberAsc(Pageable pageable);  // orderNumber 오름차순으로 프로젝트 조회
    Page<Project> findByPlatformOrderByOrderNumberAsc(String platform, Pageable pageable);  // 특정 플랫폼의 프로젝트만 조회
}
