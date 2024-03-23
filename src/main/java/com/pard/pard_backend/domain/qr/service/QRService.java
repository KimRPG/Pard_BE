package com.pard.pard_backend.domain.qr.service;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QRService {

    private final UserRepository userRepository;

//    프런트에서 찍은 QR코드 받아서 파드 출석 QR인지 아닌지 판단하는 로직
    public ResponseQrDto.attendaceResponse checkQR(RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO){
        String inputQr = qrAttendanceRequestDTO.getQrCode();
        if(inputQr.equals("https://me-qr.com/uoN4lOs1")){
            return ResponseQrDto.attendaceResponse.builder().isPardQr(true).build();
        } else {
            return ResponseQrDto.attendaceResponse.builder().isPardQr(false).build();
        }
    }

//    uid, qr출석 시간 받아서 출,지 결정하는 로직
    public void checkQrTime(RequestQrDto.QRTimeRequestDTO qrTimeRequestDTO){
        Long uid = qrTimeRequestDTO.getUid();
        LocalDateTime qrTime = qrTimeRequestDTO.getQrTime();
        User user = userRepository.findById(uid).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
//        schedule 중에서 전체 공지 중 오늘 날짜에 해당하는 것 중 제일 빠른 일정 가져오고, 그 일정시간 isBefore qrTime이면 출석, isAfter qrTime이면 지각
    }
}
