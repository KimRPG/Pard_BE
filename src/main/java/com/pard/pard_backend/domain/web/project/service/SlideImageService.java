package com.pard.pard_backend.domain.web.project.service;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.repository.SlideImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SlideImageService {
    private final SlideImageRepository slideImageRepository;

    public List<ProjectDetailResponseDto.SlideImageDto> getSlideImagesByProject(Long projectId, String apiType) {
        return slideImageRepository.findByProjectIdOrderByOrderNumber(projectId)
            .stream()
            .filter(slideImage -> slideImage.getApiType().equals(apiType)) //apiType에 맞춰서 사진 찾고
            .map(slideImage -> ProjectDetailResponseDto.SlideImageDto.builder() //builer로
                .url(slideImage.getUrl())
                .orderNumber(slideImage.getOrderNumber())
                .build())
                .toList();
    }
}
