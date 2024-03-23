package com.pard.pard_backend.domain.qr.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pard.pard_backend.global.util.CustomLocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class RequestQrDto {
    @Getter
    @Setter
    public static class QRAttendanceRequestDTO {
        private String qrCode;
    }

    @Getter
    public static class QRTimeRequestDTO {
        private Long uid;
//        yyyymmddhmmss 형식으로 받음
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
        private LocalDateTime qrTime;
    }
}
