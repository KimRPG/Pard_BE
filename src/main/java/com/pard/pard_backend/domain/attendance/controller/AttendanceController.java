package com.pard.pard_backend.domain.attendance.controller;

import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.dto.AttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.dto.UserAttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.dto.UserGeneration;
import com.pard.pard_backend.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public List<AttendanceResponseDTO> checkAttendance(@CookieValue("Authorization") String token) {
        return attendanceService.getAttendance(token);
    }

    //admin으로 박아놓기
    @GetMapping("/all/{generation}")
    public List<UserAttendanceResponseDTO> checkAllAttendance(@PathVariable String generation) {
        return attendanceService.getAllAttendance(generation);
    }

}

