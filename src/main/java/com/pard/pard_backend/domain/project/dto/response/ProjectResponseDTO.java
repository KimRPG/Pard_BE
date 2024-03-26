package com.pard.pard_backend.domain.project.dto.response;

import com.pard.pard_backend.domain.project.entity.Project;
import com.pard.pard_backend.domain.project.entity.Tool;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class ProjectResponseDTO {

    @Getter
    @Setter
    @Builder
//    @Schema(name="projectResponseHome")
    public static class Home{

        private String generation;
        private String serviceName;
        private String deviceType;
        private String title;
        private String mobileBackImg;

        public static Home toDTO(final Project project) {
            return Home.builder()
                    .generation(project.getGeneration())
                    .serviceName(project.getServiceName())
                    .deviceType(project.getDeviceType())
                    .title(project.getTitle())
                    .mobileBackImg(project.getMobileBackImg())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class Detail{

        private Long projectId;
        private String teamName;
        private String generation;
        private String deviceType;
        private String serviceName;
        private String title;
        private String contents;
        private String batch;
        private String link;
        private String mobileBackImg;
        private String backImg;
        private String mobTitle;
        private String mobContents;
        private Map<String, Tool> tool;

        public static Detail toDTO(final Project project) {
            return Detail.builder()
                    .projectId(project.getProjectId())
                    .teamName(project.getTeamName())
                    .generation(project.getGeneration())
                    .deviceType(project.getDeviceType())
                    .serviceName(project.getServiceName())
                    .title(project.getTitle())
                    .contents(project.getContents())
                    .batch(project.getBatch())
                    .link(project.getLink())
                    .mobileBackImg(project.getMobileBackImg())
                    .mobTitle(project.getMobTitle())
                    .backImg(project.getBackImg())
                    .mobContents(project.getMobContents())
                    .tool(project.getTool())
                    .build();
        }
    }
}