package com.pard.pard_backend.domain.reason.dto.response;

import com.pard.pard_backend.domain.reason.entity.Reason;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;


public class ReasonResponseDTO {
    @Getter
    @Setter
    @Builder
    public static class ReasonMinus {
        private float point;
        private String reason;
        private boolean isBonus;
        private String detail;
        private Date createAt;
        public static ReasonMinus toDto(final @NotNull Reason reason) {
            return ReasonMinus.builder()
                    .point(reason.getPoint())
                    .reason(reason.getReason())
                    .isBonus(reason.isBonus())
                    .detail(reason.getDetail())
                    .createAt(reason.getCreateDate())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class ReasonBonus {
        private float point;
        private String reason;
        private boolean isBonus;
        private String detail;
        private Date createAt;
        public static ReasonBonus toDto(final @NotNull Reason reason) {
            return ReasonBonus.builder()
                    .point(reason.getPoint())
                    .reason(reason.getReason())
                    .isBonus(reason.isBonus())
                    .detail(reason.getDetail())
                    .createAt(reason.getCreateDate())
                    .build();
        }
    }
}
