package com.pard.pard_backend.domain.user.dto.response;

import com.pard.pard_backend.domain.user.entity.Role;
import com.pard.pard_backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

public class UserResponseDTO {

    @Getter
    @Setter
    @Builder
    public static class Create{

        private Long userId;
        private String name;
        private String email;
        private String part;
//        private Long projectId;

        public static Create toDto(final @NotNull User user) {
            return Create.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .part(user.getPart())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class UserInfo {
        private String part;
        private String name;
        private Role role;
        private String generation;
        public static UserInfo toDto(final @NotNull User user) {
            return UserInfo.builder()
                    .part(user.getPart())
                    .name(user.getName())
                    .role(user.getRole())
                    .generation(user.getGeneration())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class UserBonusPoint {
        private float totalBonus;
        public static UserBonusPoint toDto(final @NotNull User user) {
            return UserBonusPoint.builder()
                    .totalBonus(user.getTotalBonus())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class UserMinusPoint {
        private float totalMinus;
        public static UserMinusPoint toDto(final @NotNull User user) {
            return UserMinusPoint.builder()
                    .totalMinus(user.getTotalMinus())
                    .build();
        }
    }
}
