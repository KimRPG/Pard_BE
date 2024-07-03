package com.pard.pard_backend.domain.attendance.repo;

import com.pard.pard_backend.domain.attendance.entity.Attendance;
import com.pard.pard_backend.domain.attendance.entity.Seminar;
import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    Attendance findByUserAndSeminar(User user, Seminar seminar);

    List<Attendance> findByUser(User user);
}
