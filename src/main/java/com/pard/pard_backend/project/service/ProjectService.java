package com.pard.pard_backend.project.service;

import com.pard.pard_backend.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.project.dto.response.ProjectResponseDTO;
import com.pard.pard_backend.project.entity.Project;
import com.pard.pard_backend.project.entity.Tool;
import com.pard.pard_backend.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public String createProject(ProjectRequestDTO.Create requestDTO) {
        //Map<String,List<String>>으로 받는데 이걸 Map<String,Tool>로 바꾸고 저장해야함
        Map<String,Tool> requestTool = new HashMap<>();
        for(String key : requestDTO.getTool().keySet()){
            requestTool.put(key,new Tool(requestDTO.getTool().get(key)));
        }
        Project project = Project.toEntity(requestDTO, requestTool);
        projectRepository.save(project);
        return "추가됨";
    }

}
