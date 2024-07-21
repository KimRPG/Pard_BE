package com.pard.pard_backend.domain.attendance.controller;

import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.dto.AttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.dto.UserAttendanceResponseDTO;
import com.pard.pard_backend.domain.attendance.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/admin")
    @Operation(summary = "사용자의 출석체크 해줍니다.", description = "입력한 정보대로 유저의 출석 정보를 수정해줍니다.")
    public String checkAttendance(@RequestBody List<AttendanceAdminRequestDTO> dto) {
        attendanceService.postListAttendance(dto);
        return "출첵 확인";
    }

    @GetMapping("")
    @Operation(summary = "입력한 쿠키의 사용자의 모든 출석 정보를 불러옵니다.", description = "쿠키를 입력하면 해당 입력 사용자의 지금까지의 출석 정보를 배열로 반환해줍니다.")
    public List<AttendanceResponseDTO> checkAttendance(@CookieValue("Authorization") String token) {
        return attendanceService.getAttendance(token);
    }

    //admin으로 박아놓기
    @GetMapping("/all/{generation}")
    @Operation(summary = "입력한 기수의 출석 정보를 불러옵니다.", description = "기수의 이름을 입력해주면, 해당 기수의 유저 정보와, 출석 정보를 담아서 반환해줍니다.")
    public List<UserAttendanceResponseDTO> checkAllAttendance(@PathVariable String generation) {
        return attendanceService.getAllAttendance(generation);
    }

}

