package com.pard.pard_backend.domain.attendance.dto;

import com.pard.pard_backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserAttendanceResponseDTO {
    private String userEmail;
    private String name;
    private String part;
    private String role;
    private List<AttendanceResponseDTO> attendances;

    public static UserAttendanceResponseDTO toDTO(User user, List<AttendanceResponseDTO> attendances) {
        return new UserAttendanceResponseDTO(user.getEmail(),user.getName(), user.getPart(), user.getRole(), attendances);
    }
}