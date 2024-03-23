package com.pard.pard_backend.domain.qr.dto.request;

import lombok.Builder;
import lombok.Getter;

public class QRDto {
    public static class QRAttendanceRequestDTO {
        @Getter
        private String qrCode;
    }
}
