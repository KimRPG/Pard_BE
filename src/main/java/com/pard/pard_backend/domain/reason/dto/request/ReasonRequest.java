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
        private boolean attendance;
    }

    @Getter
    @Setter
    @Builder
    public static class SchedulePointDTO {
        private String email;
        private float point;
        private String reason;
        public static SchedulePointDTO toDto(String email, float point,String reason) {
            return SchedulePointDTO.builder()
                    .email(email)
                    .point(point)
                    .reason(reason)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class ReasonDeleteDTO {
        private String email;
        private long reasonId;
    }
}
