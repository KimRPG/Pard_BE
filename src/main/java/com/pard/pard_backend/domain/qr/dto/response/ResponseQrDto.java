package com.pard.pard_backend.domain.qr.dto.response;

import lombok.Builder;
import lombok.Getter;

public class ResponseQrDto {
    @Getter
    @Builder
    public static class attendaceResponse{
        private boolean isPardQr;
    }
}
