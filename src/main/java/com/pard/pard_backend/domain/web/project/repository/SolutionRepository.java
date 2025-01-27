package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    // orderNumber 순으로 정렬하여 프로젝트의 solution들을 가져옴 (1,2,3 순서)
    List<Solution> findByProjectIdOrderByOrderNumber(Long projectId);
}
