package com.pard.pard_backend.domain.web.project.controller;


import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.dto.response.ProjectListResponseDto;
import com.pard.pard_backend.domain.web.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @Operation(
            summary = "프로젝트 상세 조회",
            description = "프로젝트 ID를 통해 상세 정보를 조회합니다."
    )
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponseDto> getProjectDetail(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectDetail(projectId));
    }

    // 모바일 상세조회
    @Operation(
            summary = "프로젝트 상세 조회 (모바일)",
            description = "프로젝트 ID를 통해 상세 정보를 조회합니다. (모바일용)"
    )
    @GetMapping("/mobile/{projectId}")
    public ResponseEntity<ProjectDetailResponseDto> getProjectDetailMobile(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectDetailMobile(projectId));
    }

    // 전체 프로젝트 목록 조회
    @Operation(
            summary = "전체 프로젝트 목록 조회",
            description = "전체 프로젝트 목록을 조회합니다."
    )
    @GetMapping("/projects/all") // 전체 프로젝트 목록 조회 (WEB용)
    public ResponseEntity<Page<ProjectListResponseDto>> getAllProjects(
            @RequestParam(defaultValue = "0") int page) { 
        return ResponseEntity.ok(projectService.getAllProjects(page));
    }

    // 전체 프로젝트 목록 모바일 조회
    @Operation(
            summary = "전체 프로젝트 목록 조회 (모바일)",
            description = "전체 프로젝트 목록을 조회합니다. (모바일용)"
    )
    @GetMapping("/mobile/projects/all") // 전체 프로젝트 목록 모바일 조회 (APP용)
    public ResponseEntity<Page<ProjectListResponseDto>> getAllProjectsMobile(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(projectService.getAllProjectsMobile(page));
    }

    // 웹 프로젝트 목록 조회
    @Operation(
            summary = "웹 프로젝트 목록 조회",
            description = "웹 프로젝트 목록을 조회합니다."
    )
    @GetMapping("/projects/web")
    public ResponseEntity<Page<ProjectListResponseDto>> getWebProjects(
            @RequestParam(defaultValue = "0") int page) { 
        return ResponseEntity.ok(projectService.getWebProjects(page));
    }

    // 웹 프로젝트 목록 모바일 조회
    @Operation(
            summary = "웹 프로젝트 목록 조회 (모바일)",
            description = "웹 프로젝트 목록을 조회합니다. (모바일용)"
    )
    @GetMapping("/mobile/projects/web")
    public ResponseEntity<Page<ProjectListResponseDto>> getWebProjectsMobile(
            @RequestParam(defaultValue = "0") int page) { // 페이지 번호가 없으면 0페이지
        return ResponseEntity.ok(projectService.getWebProjectsMobile(page));
    }

    // 앱 프로젝트 목록 조회
    @Operation(
            summary = "앱 프로젝트 목록 조회",
            description = "앱 프로젝트 목록을 조회합니다."
    )
    @GetMapping("/projects/app")
    public ResponseEntity<Page<ProjectListResponseDto>> getAppProjects(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(projectService.getAppProjects(page));
    }

    // 앱 프로젝트 목록 모바일 조회
    @Operation(
            summary = "앱 프로젝트 목록 조회 (모바일)",
            description = "앱 프로젝트 목록을 조회합니다. (모바일용)"
    )
    @GetMapping("/mobile/projects/app")
    public ResponseEntity<Page<ProjectListResponseDto>> getAppProjectsMobile(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(projectService.getAppProjectsMobile(page));
    }

}
