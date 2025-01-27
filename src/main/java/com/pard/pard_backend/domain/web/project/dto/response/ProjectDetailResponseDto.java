package com.pard.pard_backend.domain.web.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;



@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailResponseDto {
    private Long id;
    private String generation; //1기, 2기
    private String platform;  // WEB, APP
    private String award; //1기 롱커톤 대상
    private String serviceName;// 나노플랜
    private String teamName; //단무지

    private List<TeamDto> team;
    private ContentGroupDto contents;
    private List<ImageDto> images;
    private List<LinkDto> links;
    private List<SolutionDto> solutions;
    private List<SlideImageDto> slideImages;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamDto {
        private String role;
        private List<String> members;
        private List<String> techStacks;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TechStackDto {
        private String category;
        private String tech;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentGroupDto {
        private List<ContentDto> oneSentence;
        private List<ContentDto> description;
        private List<ContentDto> overview;
        private List<ContentDto> definition;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentDto {
        private Integer orderNumber;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDto {
        private String type;  // THUMBNAIL, LOGO, AWARD
        private String url;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkDto {
        private String linkType;  // GITHUB, FIGMA
        private String url;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SolutionDto {
        private String content;
        private Integer orderNumber;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SlideImageDto {
        private String url;
        private Integer orderNumber;
    }
}
