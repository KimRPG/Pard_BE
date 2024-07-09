package com.pard.pard_backend.domain.user.dto.response;

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
        private String role;
        private String generation;
        private Integer totalBonus;
        private float totalMinus;
        private float pangoolPoint;

        public static UserInfo toDto(final @NotNull User user) {
            return UserInfo.builder()
                    .part(user.getPart())
                    .name(user.getName())
                    .role(user.getRole())
                    .generation(user.getGeneration())
                    .totalBonus((int)user.getTotalBonus())
                    .totalMinus(user.getTotalMinus())
                    .pangoolPoint(user.getPangoolPoint())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class UserInfoAdmin {
        private String part;
        private String name;
        private String role;
        private String generation;
        private Integer totalBonus;
        private float totalMinus;
        private float pangoolPoint;
        private String userEmail;
        private String phoneNumber;

        public static UserInfoAdmin toDto(final @NotNull User user) {
            return UserInfoAdmin.builder()
                    .part(user.getPart())
                    .name(user.getName())
                    .role(user.getRole())
                    .generation(user.getGeneration())
                    .totalBonus((int)user.getTotalBonus())
                    .totalMinus(user.getTotalMinus())
                    .pangoolPoint(user.getPangoolPoint())
                    .userEmail(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
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

    @Getter
    @Setter
    @Builder
    public static class UserTopThree {
        private String part;
        private String name;
        public static UserTopThree toDto(final @NotNull User user) {
            return UserTopThree.builder()
                    .part(user.getPart())
                    .name(user.getName())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class UserGenerationAll {
        private String part;
        private String name;
        private float totalBonus;
        public static UserGenerationAll toDto(final @NotNull User user) {
            return UserGenerationAll.builder()
                    .part(user.getPart())
                    .name(user.getName())
                    .totalBonus(user.getTotalBonus())
                    .build();
        }
    }
}
