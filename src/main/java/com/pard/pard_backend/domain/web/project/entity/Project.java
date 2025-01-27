package com.pard.pard_backend.domain.web.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String generation; // 1기, 2기

    @Column
    private String platform;  // WEB, APP

    @Column
    private String award; //1기롱커톤 대상, 2기롱커톤

    @Column
    private String serviceName; //

    @Column
    private String teamName;//단무지, 에스파드

    @Column
    private Integer orderNumber;//리스트페이지에서, 배치 순서
}
