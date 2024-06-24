package com.pard.pard_backend.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequestDTO {
    @Getter
    @Setter
    @Builder
    public static class Create{

        private String name;
        private String email;
        private String part;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Jwt{
        private String email;
        private String name;
        private String role;
    }
}
