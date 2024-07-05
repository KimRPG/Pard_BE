package com.pard.pard_backend.domain.qr.service;

import com.pard.pard_backend.domain.attendance.dto.AttendanceRequestDto;
import com.pard.pard_backend.domain.attendance.service.AttendanceService;
import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class QRService {
    private final AttendanceService attendanceService;
    private final JWTUtil jwtUtil;




    //    uid, qr출석 시간 받아서 출,지 결정하는 로직
    public ResponseQrDto.attendaceResponse checkQrTime(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO, String token) {
        String userEmail = jwtUtil.getEmail(token);
//        schedule 중에서 전체 공지(isNotice = true) 중 오늘 날짜에 해당하는 것 중(date_ 제일 빠른 일정 가져오고,
//        그 일정시간 isBefore qrTime이면 출석, isAfter qrTime이면 지각

        LocalDateTime currentTime = LocalDateTime.now();
        LocalTime cutOffTime = LocalTime.of(4, 1);
        if (currentTime.toLocalTime().isAfter(cutOffTime)) {
            AttendanceRequestDto req = AttendanceRequestDto.toDTO("지각", qrAttendanceRequestDTO.getSeminar());
            attendanceService.checkAttendance(req, userEmail);
            return ResponseQrDto.attendaceResponse.builder().isAttended(false).build();
        } else { //출석
            AttendanceRequestDto req = AttendanceRequestDto.toDTO("출석", qrAttendanceRequestDTO.getSeminar());
            attendanceService.checkAttendance(req, userEmail);
        }
        return ResponseQrDto.attendaceResponse.builder().isAttended(true).build();
    }

    //    프런트에서 찍은 QR코드 받아서 파드 출석 QR인지 아닌지 판단 맞다면 출결 메서드 호출
    public ResponseQrDto.attendaceResponse checkQR(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO, String token) {
        String QRUrl = qrAttendanceRequestDTO.getQrUrl();
        switch (QRUrl) {
            case "https://me-qr.com/uoN4lOs1": //OT
                qrAttendanceRequestDTO.setSeminar("OT");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/1":
                qrAttendanceRequestDTO.setSeminar("1차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/2":
                qrAttendanceRequestDTO.setSeminar("2차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/3":
                qrAttendanceRequestDTO.setSeminar("3차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/4":
                qrAttendanceRequestDTO.setSeminar("4차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/5":
                qrAttendanceRequestDTO.setSeminar("5차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/6":
                qrAttendanceRequestDTO.setSeminar("6차_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/7":
                qrAttendanceRequestDTO.setSeminar("연합_세미나");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/8":
                qrAttendanceRequestDTO.setSeminar("연합_세미나2");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/9":
                qrAttendanceRequestDTO.setSeminar("아이디어_피칭");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            case "https://me-qr.com/10":
                qrAttendanceRequestDTO.setSeminar("종강총회");
                return this.checkQrTime(qrAttendanceRequestDTO, token);
            default:
                throw new ProjectException.WrongQR(ProjectErrorCode.WrongQR);
        }
    }


}
