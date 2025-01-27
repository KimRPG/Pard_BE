package com.pard.pard_backend.domain.web.project.service;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {
    private final ImageRepository imageRepository;

    public List<ProjectDetailResponseDto.ImageDto> getImagesByProject(Long projectId, String apiType) {
        return imageRepository.findByProjectIdAndApiType(projectId, apiType) //apiType에 맞춰서 찾고
            .stream()
            .filter(image -> !"THUMBNAIL".equals(image.getImageType()))  // THUMBNAIL 타입 제외
            .map(image -> ProjectDetailResponseDto.ImageDto.builder() //builder로 반환
                .type(image.getImageType())
                .url(image.getUrl())
                .build())
                .toList();
    }
}
