package com.pard.pard_backend.project.controller;

import com.pard.pard_backend.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.project.dto.response.ProjectResponseDTO;
import com.pard.pard_backend.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<ProjectResponseDTO.Home> createProject(@RequestBody ProjectRequestDTO.Create requestDTO) {
        ProjectResponseDTO.Home ret = projectService.createProject(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
