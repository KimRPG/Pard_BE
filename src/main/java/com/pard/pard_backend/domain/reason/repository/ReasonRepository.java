package com.pard.pard_backend.domain.reason.repository;

import com.pard.pard_backend.domain.reason.entity.Reason;
import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
    List<Reason> findByUser(User user);
}
