package com.pard.pard_backend.domain.schedule.repo;

import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    Optional<List<Schedule>> findByDateAndNoticeIsTrue(LocalDate date);

    @Query("SELECT s FROM Schedule s WHERE s.part = :part")
    Optional<List<Schedule>> findAllByPart(String part);
}

