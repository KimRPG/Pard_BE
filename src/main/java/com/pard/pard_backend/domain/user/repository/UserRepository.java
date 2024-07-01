package com.pard.pard_backend.domain.user.repository;

import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.part = :part ORDER BY u.totalBonus DESC")
    List<User> findUsersByPartOrderedByTotalBonus(@Param("part") String part);

    @Query("SELECT u from User u WHERE u.generation =:generation ORDER BY u.totalBonus DESC ")
    List<User> findUsersByGenerationOrderedByTotalBonus(@Param("generation") String generation);

    boolean existsByEmail(String email);
}
