package com.pard.pard_backend.domain.attendance.controller;

import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/admin")
    public String checkAttendance(@RequestBody AttendanceAdminRequestDTO dto) {
        attendanceService.checkAdminAttendance(dto);
        return "출첵 확인";
    }

    @PatchMapping("/patch")
    public String patchAttendance(@RequestBody AttendanceAdminRequestDTO dto) {
        attendanceService.patchAttendance(dto);
        return "출첵 변경";
    }
}
