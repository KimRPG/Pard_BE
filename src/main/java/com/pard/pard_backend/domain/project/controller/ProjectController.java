package com.pard.pard_backend.domain.project.controller;

import com.pard.pard_backend.domain.project.service.ProjectService;
import com.pard.pard_backend.domain.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.domain.project.dto.response.ProjectResponseDTO;
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
    public ResponseEntity<String> createProject(@RequestBody ProjectRequestDTO.Create requestDTO) {
        return ResponseEntity.ok(projectService.createProject(requestDTO));
    }
    //프로젝트 9개 부르는 api /projects?page= 몇번째 페이지인지
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO.Home>> getProjectPages(@RequestParam(value = "page") int pageNumber){
        return ResponseEntity.ok(projectService.getList(pageNumber));
    }
    //프로젝트 디테일 부른느 api
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO.Detail> getProjectDetail(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.getDetail(projectId));
    }
}
