package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByProjectIdAndApiTypeOrderByOrderNumber(Long projectId, String apiType);
}
