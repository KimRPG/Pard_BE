package com.pard.pard_backend.domain.project.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class ProjectRequestDTO {
    @Getter
    @Setter
    @Builder
    public static class Create{

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
        private Map<String,List<String>> tool;
    }

}
