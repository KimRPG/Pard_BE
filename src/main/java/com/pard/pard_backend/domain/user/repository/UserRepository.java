package com.pard.pard_backend.domain.user.repository;

import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.part = :part")
    Integer countUsersByPart(@Param("part") String part);

    @Query(value = "SELECT r.rank FROM (SELECT u.*, RANK() OVER (ORDER BY u.total_bonus DESC) AS rank FROM users u WHERE u.generation = (SELECT generation FROM users WHERE email = :email)) r WHERE r.email = :email", nativeQuery = true)
    Integer findUserRankByGenerationAndEmail(@Param("email") String email);

    boolean existsByEmail(String email);

    List<User> findByGeneration(String generation);
}
