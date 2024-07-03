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
        public static ReasonRequestDTO toDto(String email,float point,String reason,boolean isBonus,String detail,boolean attendance) {
            return ReasonRequestDTO.builder()
                    .email(email)
                    .point(point)
                    .reason(reason)
                    .isBonus(isBonus)
                    .detail(detail)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class SchedulePointDTO {
        private float point;
        private String reason;
        public static SchedulePointDTO toDto(float point,String reason) {
            return SchedulePointDTO.builder()
                    .point(point)
                    .reason(reason)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class SchedulePointAdmin {
        private float point;
        private String reason;
        private String email;
        public static SchedulePointAdmin toDto(float point,String reason,String email) {
            return SchedulePointAdmin.builder()
                    .point(point)
                    .reason(reason)
                    .email(email)
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
