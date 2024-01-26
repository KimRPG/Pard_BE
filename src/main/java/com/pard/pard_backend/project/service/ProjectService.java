package com.pard.pard_backend.project.service;

import com.pard.pard_backend.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.project.dto.response.ProjectResponseDTO;
import com.pard.pard_backend.project.entity.Project;
import com.pard.pard_backend.project.entity.Tool;
import com.pard.pard_backend.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.*;

@Service
@Slf4j
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

    public List<ProjectResponseDTO.Home> getList(int pageNumber){
//        프로젝트들 id 내림차순으로 list에 저장
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("projectId"));

//        정적메서드 of로 요청할 page 만듬. 페이지번호(0부터 시작),페이지 당 프로젝트 갯수, 정렬방식
        Pageable pageable = PageRequest.of(pageNumber, 9, Sort.by(sorts));

        Page<Project> pagedProjects =  this.projectRepository.findAll(pageable);
        List<ProjectResponseDTO.Home> retDto = new ArrayList<>();

        for (Project project : pagedProjects) {
            ProjectResponseDTO.Home ret = ProjectResponseDTO.Home.toDTO(project);
            retDto.add(ret);
//            log.info(String.valueOf(project.getProjectId()));     //내림차순으로 잘 부루는지 log확인
        }
//        프런트에 보내줄 값은 Home의 값들
        return retDto;
    }


    public ProjectResponseDTO.Detail getDetail(Long projectId) {
        Optional<Project> p = projectRepository.findById(projectId);
        if(p.isPresent()){
            Project project = p.get();
            return ProjectResponseDTO.Detail.toDTO(project);
        } else {
            return null;
        }
    }
}
