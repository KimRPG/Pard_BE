package com.pard.pard_backend.domain.attendance.dto;

import com.pard.pard_backend.domain.attendance.entity.AttendanceStatus;
import com.pard.pard_backend.domain.attendance.entity.Seminar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AttendanceAdminRequestDTO {
    private AttendanceStatus status;
    private Seminar seminar;
    private String email;


}
