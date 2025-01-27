package com.pard.pard_backend.domain.web.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListResponseDto {
    private Long id;  // 프로젝트 ID
    private String generation;  // 기수 정보
    private String platform;  // WEB APP
    private Integer orderNumber;  // 정렬 순서
    private String thumbnailUrl;  // 대표 이미지
}
