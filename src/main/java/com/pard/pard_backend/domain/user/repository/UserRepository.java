package com.pard.pard_backend.domain.user.repository;

import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);

    List<User> findByGeneration(String generation);

    List<User> findByGenerationOrderByTotalBonusDesc(String generation);
    List<User> findTop3ByGenerationOrderByTotalBonusDesc(String generation);

    void deleteByEmail(String email);
}
