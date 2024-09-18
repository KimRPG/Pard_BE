package com.pard.pard_backend.domain.schedule.repo;

import com.pard.pard_backend.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.part = :part")
    Optional<List<Schedule>> findAllByPart(String part);

    @Modifying
    @Query("UPDATE Schedule s SET s.isPastEvent = true WHERE s.date < CURRENT_DATE AND s.isPastEvent = false")
    void markPastEvents();

    @Query("SELECT s FROM Schedule s WHERE s.isPastEvent = false ORDER BY s.date ASC")
    List<Schedule> findActiveSchedules();

    @Query("select s FROM Schedule s WHERE s.isPastEvent=true ORDER BY s.date DESC ")
    List<Schedule> findPastSchedulesOrderByDate();
}

