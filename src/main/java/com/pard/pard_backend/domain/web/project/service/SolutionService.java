package com.pard.pard_backend.domain.web.project.service;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SolutionService {
    private final SolutionRepository solutionRepository;
    
    public List<ProjectDetailResponseDto.SolutionDto> getSolutionsByProject(Long projectId) {
        return solutionRepository.findByProjectIdOrderByOrderNumber(projectId)// orderNumber로 정렬하여 솔루션 조회
            .stream()

            .map(solution -> ProjectDetailResponseDto.SolutionDto.builder()// DTO변환
                .content(solution.getContent())
                .orderNumber(solution.getOrderNumber())
                .build())
                .toList();
    }
}
