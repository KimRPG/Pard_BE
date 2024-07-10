package com.pard.pard_backend.domain.reason.dto.request;

import lombok.*;

public class ReasonRequest {
    @Getter
    @Setter
    @Builder
    public static class ReasonRequestDTO {
        private String email;
        private float point;
        private String reason;
        private boolean isBonus;
        private String title;
        private String detail;
        public static ReasonRequestDTO toDto(String email,Integer point,String reason,boolean isBonus,String detail,String title) {
            return ReasonRequestDTO.builder()
                    .email(email)
                    .point(point)
                    .reason(reason)
                    .isBonus(isBonus)
                    .title(title)
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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReasonDeleteDTO {
        private long reasonId;
    }
}
