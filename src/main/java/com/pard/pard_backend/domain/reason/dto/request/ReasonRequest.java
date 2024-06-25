package com.pard.pard_backend.domain.reason.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ReasonRequest {
    @Getter
    @Setter
    @Builder
    public static class ReasonRequestDTO {
        private String email;
        private float point;
        private String reason;
        private boolean isBonus;
        private String detail;
    }
    @Getter
    @Setter
    @Builder
    public static class ReasonDeleteDTO {
        private String email;
        private long reasonId;
    }
}
