package com.pard.pard_backend.domain.qr.service;

import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.schedule.service.ScheduleService;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QRService {

    private final UserRepository userRepository;

    private final ScheduleService scheduleService;

//    프런트에서 찍은 QR코드 받아서 파드 출석 QR인지 아닌지 판단 맞다면 출결 메서드 호출
    public ResponseQrDto.attendaceResponse checkQR(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO){
        String QRUrl = qrAttendanceRequestDTO.getQRUrl();
        if(QRUrl.equals("https://me-qr.com/uoN4lOs1")){
            return this.checkQrTime(qrAttendanceRequestDTO);
        } else {
            throw new ProjectException.WrongQR(ProjectErrorCode.WrongQR);
        }
    }

//    uid, qr출석 시간 받아서 출,지 결정하는 로직
    public ResponseQrDto.attendaceResponse checkQrTime(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO){
        Long uid = qrAttendanceRequestDTO.getUid();
        Timestamp userQrTime = qrAttendanceRequestDTO.getTime();
        User user = userRepository.findById(uid).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
//        schedule 중에서 전체 공지(isNotice = true) 중 오늘 날짜에 해당하는 것 중(date_ 제일 빠른 일정 가져오고,
//        그 일정시간 isBefore qrTime이면 출석, isAfter qrTime이면 지각
        Timestamp todayQRTime = scheduleService.getTodayQRTime();
        if (userQrTime != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(todayQRTime.getTime());

            // 1시 1분 부터 지각
            cal.add(Calendar.MINUTE, 1);

            Timestamp oneMinuteAfterTodayQRTime = new Timestamp(cal.getTime().getTime());
            log.info(String.valueOf(oneMinuteAfterTodayQRTime));

            if(userQrTime.after(oneMinuteAfterTodayQRTime)) { //지각

            } else{ //출석

            }
        } else {
            throw new ProjectException.NoUserQRTime(ProjectErrorCode.NoUserQRTime);
        }
        return ResponseQrDto.attendaceResponse.builder()
                .isAttended(true)
                .build();
    }
}
