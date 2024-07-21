package com.pard.pard_backend.domain.qr.controller;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.service.ReasonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class QRController {
    private final QRService qrService;

    @PostMapping("/validQR")
    @Operation(summary = "입력한 쿠키 사용자가 qr을 찍으면 해당 qr이 파드의 유효 qr인지 확인하고, 출석체크", description = "해당 사용자의 해당 세미나 attendance 체크해줍니다.")
    public ResponseQrDto.attendaceResponse validQR(@RequestBody RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO,@CookieValue(value = "Authorization") String token){
        return qrService.checkQR(qrAttendanceRequestDTO,token);
    }



}
