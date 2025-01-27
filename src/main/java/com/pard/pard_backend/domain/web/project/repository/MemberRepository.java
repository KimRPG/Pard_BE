package com.pard.pard_backend.domain.web.project.repository;

import com.pard.pard_backend.domain.web.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByProjectId(Long projectId);
}
