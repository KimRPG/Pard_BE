package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
    List<TechStack> findByProjectId(Long projectId);
}
