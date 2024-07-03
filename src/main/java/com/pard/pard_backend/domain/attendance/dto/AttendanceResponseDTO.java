package com.pard.pard_backend.domain.attendance.dto;

import com.pard.pard_backend.domain.attendance.entity.Attendance;
import com.pard.pard_backend.domain.attendance.entity.AttendanceStatus;
import com.pard.pard_backend.domain.attendance.entity.Seminar;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttendanceResponseDTO {
    private AttendanceStatus status;
    private Seminar seminar;

    public static AttendanceResponseDTO toDTO(Attendance attendance) {
        return AttendanceResponseDTO.builder()
                .seminar(attendance.getSeminar())
                .status(attendance.getStatus())
                .build();
    }
}
