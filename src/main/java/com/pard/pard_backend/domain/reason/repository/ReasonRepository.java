package com.pard.pard_backend.domain.reason.repository;

import com.pard.pard_backend.domain.reason.entity.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
}
