package com.pard.pard_backend.domain.qr.controller;

import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.service.ReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QRController {
    private final QRService qrService;
    private final ReasonService reasonService;

    @GetMapping("/validQR")
    public ResponseQrDto.attendaceResponse validQR(@RequestBody RequestQrDto.QRAttendanceRequestDTO qrAttendanceRequestDTO){
        return qrService.checkQR(qrAttendanceRequestDTO);
    }

    @PostMapping("/qr")
    public void addSchedulePoint(@RequestBody ReasonRequest.SchedulePointDTO req){
        reasonService.addSchedulePoint(req);
    }
}
