package com.pard.pard_backend.project.dto.response;

import com.pard.pard_backend.project.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
}
