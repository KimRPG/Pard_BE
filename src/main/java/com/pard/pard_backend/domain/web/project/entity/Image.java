package com.pard.pard_backend.domain.web.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column
    private String imageType;  // "LOGO", "AWARD", "THUMBNAIL"

    @Column
    private  String apiType; //WEB, APP

    @Column
    private String url;

    
}
