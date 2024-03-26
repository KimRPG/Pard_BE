package com.pard.pard_backend.domain.qr.controller;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QRController {
    private final QRService qrService;

    @GetMapping("/validQR")
    public ResponseQrDto.attendaceResponse validQR(@RequestBody RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO){
        return qrService.checkQR(qrAttendanceRequestDTO);
    }
}
