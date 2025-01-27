package com.pard.pard_backend.domain.web.project.service;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.dto.response.ProjectListResponseDto;
import com.pard.pard_backend.domain.web.project.entity.Project;
import com.pard.pard_backend.domain.web.project.entity.Image;
import com.pard.pard_backend.domain.web.project.repository.ImageRepository;
import com.pard.pard_backend.domain.web.project.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;
    private final TeamService teamService;
    private final ContentService contentService;
    private final ImageService imageService;
    private final SlideImageService slideImageService;
    private final LinkService linkService;
    private final SolutionService solutionService;

    //프로젝트 상세조회
    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new EntityNotFoundException("Project not found")); //projectId로 찾기

        return ProjectDetailResponseDto.builder()
            .id(project.getId())
            .generation(project.getGeneration())
            .platform(project.getPlatform())
            .award(project.getAward())
            .serviceName(project.getServiceName())
            .teamName(project.getTeamName())
            .team(teamService.getTeamsByProject(projectId))
            .contents(contentService.getContentsByProject(projectId, "WEB"))
            .images(imageService.getImagesByProject(projectId, "WEB"))
            .slideImages(slideImageService.getSlideImagesByProject(projectId, "WEB"))
            .links(linkService.getLinksByProject(projectId))
            .solutions(solutionService.getSolutionsByProject(projectId))
            .build();
    }

    //프로젝트 상세조회 모바일버전
    public ProjectDetailResponseDto getProjectDetailMobile(Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        return ProjectDetailResponseDto.builder()
            .id(project.getId())
            .generation(project.getGeneration())
            .platform(project.getPlatform())
            .award(project.getAward())
            .serviceName(project.getServiceName())
            .teamName(project.getTeamName())
            .team(teamService.getTeamsByProject(projectId))
            .contents(contentService.getContentsByProject(projectId, "APP"))
            .images(imageService.getImagesByProject(projectId, "APP"))
            .slideImages(slideImageService.getSlideImagesByProject(projectId, "APP"))
            .links(linkService.getLinksByProject(projectId))
            .solutions(solutionService.getSolutionsByProject(projectId))
            .build();
    }

    public Page<ProjectListResponseDto> getAllProjects(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findAllByOrderByOrderNumberAsc(pageRequest)  // orderNumber순으로 프로젝트 조회
            .map(project -> toProjectListDto(project, "WEB"));  // WEB용 DTO로 변환
    }

    public Page<ProjectListResponseDto> getAllProjectsMobile(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findAllByOrderByOrderNumberAsc(pageRequest)  // orderNumber순으로 프로젝트 조회
            .map(project -> toProjectListDto(project, "APP"));  // APP용 DTO로 변환 (썸네일이 APP인 것만 포함)
    }

    public Page<ProjectListResponseDto> getWebProjects(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findByPlatformOrderByOrderNumberAsc("WEB", pageRequest)  // WEB 플랫폼 프로젝트만 조회
            .map(project -> toProjectListDto(project, "WEB"));  // WEB용 DTO로 변환
    }

    public Page<ProjectListResponseDto> getWebProjectsMobile(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findByPlatformOrderByOrderNumberAsc("WEB", pageRequest)  // WEB 플랫폼 프로젝트만 조회
            .map(project -> toProjectListDto(project, "APP"));  // APP용 썸네일로 변환
    }

    public Page<ProjectListResponseDto> getAppProjects(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findByPlatformOrderByOrderNumberAsc("APP", pageRequest)  // APP 플랫폼 프로젝트만 조회
            .map(project -> toProjectListDto(project, "WEB"));  // WEB용 썸네일로 변환
    }

    public Page<ProjectListResponseDto> getAppProjectsMobile(int page) {
        PageRequest pageRequest = PageRequest.of(page, 9);  // 한 페이지당 9개씩 가져오도록 설정
        
        return projectRepository.findByPlatformOrderByOrderNumberAsc("APP", pageRequest)  // APP 플랫폼 프로젝트만 조회
            .map(project -> toProjectListDto(project, "APP"));  // APP용 썸네일로 변환
    }

    private ProjectListResponseDto toProjectListDto(Project project, String apiType) {
        String thumbnailUrl = imageRepository
            .findByProjectIdAndImageTypeAndApiType(project.getId(), "THUMBNAIL", apiType)  // 프로젝트의 썸네일 이미지 조회
            .map(Image::getUrl)  // 이미지 URL 추출
            .orElse(null);  // 이미지가 없으면 null 반환

        return ProjectListResponseDto.builder()  // 프로젝트 정보를 DTO로 변환
            .id(project.getId())
            .generation(project.getGeneration())
            .platform(project.getPlatform())
            .orderNumber(project.getOrderNumber())
            .thumbnailUrl(thumbnailUrl)
            .build();
    }
}
