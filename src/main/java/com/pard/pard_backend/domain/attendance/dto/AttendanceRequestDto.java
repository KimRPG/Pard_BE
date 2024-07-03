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
public class AttendanceRequestDto {
    private AttendanceStatus status;
    private Seminar seminar;

    public static AttendanceRequestDto toDTO(String status, String seminar) {
        return AttendanceRequestDto.builder()
                .status(AttendanceStatus.valueOf(status))
                .seminar(parseSeminar(seminar))
                .build();
    }


    // Helper method to parse AttendanceStatus

    // Helper method to parse Seminar
    private static Seminar parseSeminar(String seminar) {
        return switch (seminar.toUpperCase().replace(" ", "_")) {
            case "OT" -> Seminar.OT;
            case "1차_세미나" -> Seminar.SEMINAR_1;
            case "2차_세미나" -> Seminar.SEMINAR_2;
            case "3차_세미나" -> Seminar.SEMINAR_3;
            case "4차_세미나" -> Seminar.SEMINAR_4;
            case "5차_세미나" -> Seminar.SEMINAR_5;
            case "6차_세미나" -> Seminar.SEMINAR_6;
            case "연합_세미나" -> Seminar.UNION_SEMINAR_1;
            case "연합_세미나_2" -> Seminar.UNION_SEMINAR_2;
            case "아이디어_피칭" -> Seminar.IDEA_PITCH;
            case "종강_총회" -> Seminar.FINAL_MEETING;
            default -> throw new IllegalArgumentException("Unknown seminar: " + seminar);
        };
    }

}
