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
        private long reasonId;
        private float point;
        private String reason;
        private boolean isBonus;
        private String detail;
        private Date createAt;
        public static ReasonMinus toDto(final @NotNull Reason reason) {
            return ReasonMinus.builder()
                    .reasonId(reason.getReasonId())
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
        private long reasonId;
        private float point;
        private String reason;
        private boolean isBonus;
        private String detail;
        private Date createAt;
        public static ReasonBonus toDto(final @NotNull Reason reason) {
            return ReasonBonus.builder()
                    .reasonId(reason.getReasonId())
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
    public static class UserPoint{
        private Integer totalBonus;
        private float totalMinus;
        private float pangoolPoint;
        public static UserPoint toDto(final @NotNull User user) {
            return UserPoint.builder()
                    .totalBonus((int)user.getTotalBonus())
                    .totalMinus(user.getTotalMinus())
                    .pangoolPoint(user.getPangoolPoint())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class UserRank{
        private Integer partRanking;
        private Integer totalRanking;
    }
    @Getter
    @Setter
    @Builder
    public static class RankInfo{
        private Integer rank;
        private String name;
        private String part;
        private Integer totalBonusPoint;
    }
}
