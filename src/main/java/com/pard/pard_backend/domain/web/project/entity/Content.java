package com.pard.pard_backend.domain.web.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column
    private String apiType;  // WEB" or APP

    @Column
    private String contentType;  // ONE_SENTENCE, OVERVIEW, DEFINITION, DESCRIPTION

    @Column
    private String content;

    @Column
    private Integer orderNumber;
}
