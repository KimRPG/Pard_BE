package com.pard.pard_backend.domain.qr.dto.request;

import lombok.Getter;
import lombok.Setter;

public class RequestQrDto {
    @Getter
    @Setter
    public static class QRAttendanceRequestDTO {
        private String qrUrl;
        private String seminar;
        //        yyyymmddhmmss 형식으로 받음

    }

}
