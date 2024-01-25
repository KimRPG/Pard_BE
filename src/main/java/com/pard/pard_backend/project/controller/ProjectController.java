package com.pard.pard_backend.project.controller;

import com.pard.pard_backend.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.project.dto.response.ProjectResponseDTO;
import com.pard.pard_backend.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        String hi=projectService.createProject(requestDTO);
        return ResponseEntity.ok(hi);
    }
    //프로젝트 9개 부르는 api /projects?page= 몇번째 페이지인지
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO.Home>> getProjectPages(@RequestParam(value = "page") int pageNumber){
        List<ProjectResponseDTO.Home> ret = projectService.getList(pageNumber);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
