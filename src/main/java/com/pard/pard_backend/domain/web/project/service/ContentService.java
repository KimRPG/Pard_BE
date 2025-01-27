package com.pard.pard_backend.domain.web.project.service;

import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.entity.Content;
import com.pard.pard_backend.domain.web.project.repository.ContentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
    private final ContentRepository contentRepository;

    public ProjectDetailResponseDto.ContentGroupDto getContentsByProject(Long projectId, String apiType) {
        List<Content> contents = contentRepository.findByProjectIdAndApiTypeOrderByOrderNumber(projectId, apiType); //apiType맞춰서 찾고
        
        Map<String, List<Content>> contentsByType = contents.stream()
            .collect(Collectors.groupingBy(Content::getContentType));
        //콘텐츠들을 contentType별로 그룹화

        return ProjectDetailResponseDto.ContentGroupDto.builder()
            .oneSentence(toContentDto(contentsByType.getOrDefault("ONE_SENTENCE", List.of())))
            .description(toContentDto(contentsByType.getOrDefault("DESCRIPTION", List.of())))
            .overview(toContentDto(contentsByType.getOrDefault("OVERVIEW", List.of())))
            .definition(toContentDto(contentsByType.getOrDefault("DEFINITION", List.of())))
            .build();
    }


    private List<ProjectDetailResponseDto.ContentDto> toContentDto(List<Content> contents) {
        return contents.stream()
            .map(content -> ProjectDetailResponseDto.ContentDto.builder()
                .orderNumber(content.getOrderNumber())
                .content(content.getContent())
                .build())
             .toList();
    }
}
