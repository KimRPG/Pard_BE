package com.pard.pard_backend.domain.qr.service;

import com.pard.pard_backend.domain.attendance.dto.AttendanceRequestDto;
import com.pard.pard_backend.domain.attendance.service.AttendanceService;
import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.schedule.service.ScheduleService;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
@Slf4j
@RequiredArgsConstructor


public class QRService {
    private final AttendanceService attendanceService;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final ScheduleService scheduleService;

    private final ReasonService reasonService;

    private final JWTUtil jwtUtil;

//    프런트에서 찍은 QR코드 받아서 파드 출석 QR인지 아닌지 판단 맞다면 출결 메서드 호출
    public ResponseQrDto.attendaceResponse checkQR(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO,String token, Timestamp currentTime){
        String QRUrl = qrAttendanceRequestDTO.getQRUrl();
        if(QRUrl.equals("https://me-qr.com/uoN4lOs1")){
            return this.checkQrTime(qrAttendanceRequestDTO,token,currentTime);
        } else {
            throw new ProjectException.WrongQR(ProjectErrorCode.WrongQR);
        }
    }

//    uid, qr출석 시간 받아서 출,지 결정하는 로직
    public ResponseQrDto.attendaceResponse checkQrTime(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO,String token,Timestamp currentTime){
        String  userEmail = jwtUtil.getEmail(token);
        User user = userRepository.findByEmail(userEmail);
        if(user == null){throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
//        schedule 중에서 전체 공지(isNotice = true) 중 오늘 날짜에 해당하는 것 중(date_ 제일 빠른 일정 가져오고,
//        그 일정시간 isBefore qrTime이면 출석, isAfter qrTime이면 지각
        Timestamp todayQRTime = scheduleService.getTodayQRTime();
        if (currentTime != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(todayQRTime.getTime());
            // 1시 1분 부터 지각
            cal.add(Calendar.MINUTE, 1);
            Timestamp oneMinuteAfterTodayQRTime = new Timestamp(cal.getTime().getTime());

            if(currentTime.after(oneMinuteAfterTodayQRTime)) { //지각
                ReasonRequest.SchedulePointDTO req = ReasonRequest.SchedulePointDTO.toDto( 4f,"지각");
                reasonService.addSchedulePoint(req,userEmail);
            } else{ //출석
                AttendanceRequestDto req = AttendanceRequestDto.toDTO("출석",qrAttendanceRequestDTO.getSeminar());
                attendanceService.checkAttendance(req,userEmail);
            }
        } else {
            throw new ProjectException.NoUserQRTime(ProjectErrorCode.NoUserQRTime);
        }
        return ResponseQrDto.attendaceResponse.builder()
                .isAttended(true)
                .build();
    }

//    프런트에서 찍은 QR코드 받아서 파드 출석 QR인지 아닌지 판단 맞다면 출결 메서드 호출
    public ResponseQrDto.attendaceResponse checkQR(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO,String token){
        String QRUrl = qrAttendanceRequestDTO.getQRUrl();
        if(QRUrl.equals("https://me-qr.com/uoN4lOs1")){
            return checkQrTime(qrAttendanceRequestDTO,token);
        } else {
            throw new ProjectException.WrongQR(ProjectErrorCode.WrongQR);
        }
    }


}
