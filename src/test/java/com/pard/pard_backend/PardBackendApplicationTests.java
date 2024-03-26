package com.pard.pard_backend;

import com.pard.pard_backend.domain.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.domain.project.service.ProjectService;
import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PardBackendApplicationTests {

    @Autowired
    private QRService qrService;

    @Test
    public void testValidQr(){
        QRService qrService1 = new QRService();

        // 유효한 QR 코드로 테스트
        RequestQrDto.QRAttendanceRequestDTO validQRRequest = new RequestQrDto.QRAttendanceRequestDTO();
        validQRRequest.setQrCode("https://me-qr.com/uoN4lOs1");
        ResponseQrDto.attendaceResponse response = qrService.checkQR(validQRRequest);

        // 검증: 유효한 QR 코드일 경우 isPardQr은 true여야 함
        Assertions.assertTrue(response.isPardQr());
    }
    @Test
    public void testInvalidQRCode() {
        // QRService 인스턴스 생성
        QRService qrService = new QRService();

        // 유효하지 않은 QR 코드로 테스트
        RequestQrDto.QRAttendanceRequestDTO invalidQRRequest = new RequestQrDto.QRAttendanceRequestDTO();
        invalidQRRequest.setQrCode("https://me-qr.com/invalidQRCode");
        ResponseQrDto.attendaceResponse response = qrService.checkQR(invalidQRRequest);

        // 검증: 유효하지 않은 QR 코드일 경우 isPardQr은 false여야 함
        Assertions.assertFalse(response.isPardQr());
    }
}
