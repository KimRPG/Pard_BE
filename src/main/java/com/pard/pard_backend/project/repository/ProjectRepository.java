package com.pard.pard_backend.project.repository;

import com.pard.pard_backend.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
