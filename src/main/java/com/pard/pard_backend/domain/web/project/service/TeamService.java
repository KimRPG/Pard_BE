package com.pard.pard_backend.domain.web.project.service;



import com.pard.pard_backend.domain.web.project.dto.response.ProjectDetailResponseDto;
import com.pard.pard_backend.domain.web.project.entity.Member;
import com.pard.pard_backend.domain.web.project.entity.TechStack;
import com.pard.pard_backend.domain.web.project.repository.MemberRepository;
import com.pard.pard_backend.domain.web.project.repository.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {
    private final MemberRepository memberRepository;
    private final TechStackRepository techStackRepository;

    public List<ProjectDetailResponseDto.TeamDto> getTeamsByProject(Long projectId) {
        
        Map<String, List<Member>> membersByRole = memberRepository.findByProjectId(projectId)
            .stream()
            .collect(Collectors.groupingBy(Member::getRole));
        //멤버들을 role별로 그룹화

        Map<String, List<TechStack>> techsByRole = techStackRepository.findByProjectId(projectId)
            .stream()
            .collect(Collectors.groupingBy(TechStack::getCategory));
        //기술 스택을 역할별로 그룹화

        return Stream.of("개발", "디자인", "기획")
            .map(role -> ProjectDetailResponseDto.TeamDto.builder()
                .role(role)
                .members(membersByRole.getOrDefault(role, List.of()).stream()
                    .map(Member::getName)
                    .toList())//role별로 멤버 리스트 만들기
                .techStacks(techsByRole.getOrDefault(role, List.of()).stream()
                    .map(TechStack::getTech)
                    .toList())//role별로 기술스텍 리스트 만들기
                .build())
            .toList();
    }
}
