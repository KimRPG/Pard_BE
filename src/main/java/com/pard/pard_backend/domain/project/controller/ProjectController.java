package com.pard.pard_backend.domain.project.controller;

import com.pard.pard_backend.domain.project.service.ProjectService;
import com.pard.pard_backend.domain.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.domain.project.dto.response.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    @Operation(summary = "프로젝트를 추가해줍니다.", description = "입력한 정보로 새 프로젝트를 생성해줍니다.")
    public ResponseEntity<String> createProject(@RequestBody ProjectRequestDTO.Create requestDTO) {
        return ResponseEntity.ok(projectService.createProject(requestDTO));
    }
    //프로젝트 9개 부르는 api /projects?page= 몇번째 페이지인지
    @GetMapping("/projects")
    @Operation(summary = "저장되어 있는 프로젝트를 전부 불러옵니다.", description = "약식으로 되어있는 프로젝트 정보를 불러옵니다.")
    public ResponseEntity<List<ProjectResponseDTO.Home>> getProjectPages(@RequestParam(value = "page") int pageNumber){
        return ResponseEntity.ok(projectService.getList(pageNumber));
    }
    //프로젝트 디테일 부른느 api
    @GetMapping("/{projectId}")
    @Operation(summary = "지정한 프로젝트의 세부 정보를 불러옵니다.", description = "보내준 프로젝 id의 약식 프로젝트 정보를 포함한 세부 정보를 전부 불러옵니다.")
    public ResponseEntity<ProjectResponseDTO.Detail> getProjectDetail(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.getDetail(projectId));
    }
}
