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

    public ProjectResponseDTO.Home createProject(ProjectRequestDTO.Create requestDTO) {
        //Map<String,List<String>>으로 받는데 이걸 Map<String,Tool>로 바꾸고 저장해야함
        Map<String,Tool> requestTool = new HashMap<>();
        for(String key : requestDTO.getTool().keySet()){
            requestTool.put(key,new Tool(requestDTO.getTool().get(key)));
        }

        Project project = Project.builder()
                .generation(requestDTO.getGeneration())
                .serviceName(requestDTO.getServiceName())
                .deviceType(requestDTO.getDeviceType())
                .backImg(requestDTO.getBackImg())
                .batch(requestDTO.getBatch())
                .contents(requestDTO.getContents())
                .link(requestDTO.getLink())
                .mobContents(requestDTO.getMobContents())
                .mobileBackImg(requestDTO.getMobileBackImg())
                .mobTitle(requestDTO.getMobTitle())
                .teamName(requestDTO.getTeamName())
                .title(requestDTO.getTitle())
                .tool(requestTool)
                .build();
        projectRepository.save(project);
        return ProjectResponseDTO.Home.toDTO(project);
    }
}
