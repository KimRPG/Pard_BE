package com.pard.pard_backend.domain.web.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;  // "PLANNING", "DEVELOPMENT", "DESIGN"

    @Column
    private String tech;  // "FIGMA", "NOTION", "JAVA"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
