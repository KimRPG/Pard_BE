package com.pard.pard_backend.domain.qr.service;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QRService {

    private final UserRepository userRepository;

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
        Timestamp qrTime = qrAttendanceRequestDTO.getTime();
        User user = userRepository.findById(uid).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
//        schedule 중에서 전체 공지(isNotice = true) 중 오늘 날짜에 해당하는 것 중(date_ 제일 빠른 일정 가져오고,
//        그 일정시간 isBefore qrTime이면 출석, isAfter qrTime이면 지각
    }
}
