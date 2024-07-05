package com.pard.pard_backend.domain.qr.controller;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.service.ReasonService;
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
    public ResponseQrDto.attendaceResponse validQR(@RequestBody RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO,@CookieValue(value = "Authorization") String token){
        return qrService.checkQR(qrAttendanceRequestDTO,token);
    }



}
