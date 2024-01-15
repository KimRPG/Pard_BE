package com.pard.pard_backend.user.repository;

import com.pard.pard_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
