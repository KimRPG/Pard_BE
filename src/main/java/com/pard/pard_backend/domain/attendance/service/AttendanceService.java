package com.pard.pard_backend.domain.attendance.service;

import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.dto.AttendanceRequestDto;
import com.pard.pard_backend.domain.attendance.dto.AttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.dto.UserAttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.entity.Attendance;
import com.pard.pard_backend.domain.attendance.repo.AttendanceRepo;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    @Transactional
    public void checkAttendance(AttendanceRequestDto dto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        user.setPangoolPoint(user.getPangoolPoint()+dto.getStatus().getPoint());
        attendanceRepo.save(Attendance.toEntity(dto, user));
    }


    @Transactional
    public void checkAdminAttendance(AttendanceAdminRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        user.setPangoolPoint(user.getPangoolPoint()+dto.getStatus().getPoint());
        attendanceRepo.save(Attendance.toEntity(dto, user));
    }

    @Transactional
    public void patchListAttendance(List<AttendanceAdminRequestDTO> dto) {
        dto.forEach(this::patchAttendance);
    }

    @Transactional
    public void patchAttendance(AttendanceAdminRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()->new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        Attendance attendance = attendanceRepo.findByUserAndSeminar(user, dto.getSeminar());
        user.setPangoolPoint(user.getPangoolPoint() - attendance.getStatus().getPoint() + dto.getStatus().getPoint());
        attendance.setStatus(dto.getStatus());
    }


    public List<AttendanceResponseDTO> getAttendance(String token) {
        User user = userRepository.findByEmail(jwtUtil.getEmail(token)).orElseThrow(()->new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        return attendanceRepo.findByUser(user).stream()
                .map(AttendanceResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserAttendanceResponseDTO> getAllAttendance(String generation) {
        // 모든 사용자에 대한 출석 정보 조회
        List<User> users = userRepository.findByGeneration(generation);

        // 각 사용자에 대한 출석 정보를 포함하는 DTO 리스트 생성
        return users.stream()
                .map(user -> {
                    List<AttendanceResponseDTO> attendances = attendanceRepo.findByUser(user).stream()
                            .map(AttendanceResponseDTO::toDTO)
                            .collect(Collectors.toList());
                    return UserAttendanceResponseDTO.toDTO(user, attendances);
                })
                .collect(Collectors.toList());
    }
}
