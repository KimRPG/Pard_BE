package com.pard.pard_backend.domain.web.project.service;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkService {
    private final LinkRepository linkRepository;

    public List<ProjectDetailResponseDto.LinkDto> getLinksByProject(Long projectId) {
        return linkRepository.findByProjectIdOrderByLinkType(projectId)  //프로젝트 ID로 링크들을 조회 (linkType 기준으로 정렬됨)
            .stream()
            .map(link -> ProjectDetailResponseDto.LinkDto.builder()  // LinkDto로 변환
                .linkType(link.getLinkType())  // 링크 타입 (예: "GITHUB", "FIGMA")
                .url(link.getUrl())  // 링크 URL 설정
                .build())  // DTO 객체 생성 완료
                .toList();
    }
}
