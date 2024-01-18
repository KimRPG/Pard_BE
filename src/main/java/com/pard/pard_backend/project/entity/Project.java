package com.pard.pard_backend.project.entity;

import com.pard.pard_backend.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long projectId;

    private String teamName;

    private String generation;

    private String deviceType;

    private String serviceName;

    private String title;

    private String contents;

    private String batch;

    private String link;

    @ElementCollection
    @CollectionTable(name = "TOOL", joinColumns = @JoinColumn(name = "PROJECT_ID"))
    @MapKeyColumn(name = "tool_id")
    private Map<String,Tool> tool;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> user;

    private String mobileBackImg;

    private String backImg;

    private String mobTitle;

    private String mobContents;

    public static Project toEntity(final ProjectRequestDTO.Create request,  Map<String,Tool> tool){

        return Project.builder()
                .generation(request.getGeneration())
                .serviceName(request.getServiceName())
                .deviceType(request.getDeviceType())
                .backImg(request.getBackImg())
                .batch(request.getBatch())
                .contents(request.getContents())
                .link(request.getLink())
                .mobContents(request.getMobContents())
                .mobileBackImg(request.getMobileBackImg())
                .mobTitle(request.getMobTitle())
                .teamName(request.getTeamName())
                .title(request.getTitle())
                .tool(tool)
                .build();
    }


}
