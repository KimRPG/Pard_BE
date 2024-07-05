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
        public static ReasonRequestDTO toDto(String email,Integer point,String reason,boolean isBonus,String detail,boolean attendance) {
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
        private Integer point;
        private String reason;
        public static SchedulePointDTO toDto(Integer point,String reason) {
            return SchedulePointDTO.builder()
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
