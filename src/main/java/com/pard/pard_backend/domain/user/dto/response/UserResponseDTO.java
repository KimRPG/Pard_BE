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
        private String level;
        private String part;
//        private Long projectId;

        public static Create toDto(final @NotNull User user) {
            return Create.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .level(user.getLevel())
                    .part(user.getPart())
                    .build();
        }
    }
}
