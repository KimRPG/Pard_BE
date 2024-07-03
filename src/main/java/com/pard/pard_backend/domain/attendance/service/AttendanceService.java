package com.pard.pard_backend.domain.attendance.service;

import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.dto.AttendanceRequestDto;
import com.pard.pard_backend.domain.attendance.entity.Attendance;
import com.pard.pard_backend.domain.attendance.repo.AttendanceRepo;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;
    private final UserRepository userRepository;
    @Transactional
    public void checkAttendance(AttendanceRequestDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("Invalid user email"));
        user.setPangoolPoint(user.getPangoolPoint()+dto.getStatus().getPoint());
        attendanceRepo.save(Attendance.toEntity(dto, user));
    }


    @Transactional
    public void checkAdminAttendance(AttendanceAdminRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid user email"));
        user.setPangoolPoint(user.getPangoolPoint()+dto.getStatus().getPoint());
        attendanceRepo.save(Attendance.toEntity(dto, user));
    }

    @Transactional
    public void patchAttendance(AttendanceAdminRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid user email"));
        Attendance attendance = attendanceRepo.findByUserAndSeminar(user, dto.getSeminar());
        user.setPangoolPoint(user.getPangoolPoint() - attendance.getStatus().getPoint() + dto.getStatus().getPoint());
        attendance.setStatus(dto.getStatus());
    }

}
