package com.pard.pard_backend.project.repository;

import com.pard.pard_backend.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);
}
