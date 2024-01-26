package com.pard.pard_backend.domain.user.dto.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class UserRequestDTO {
    @Getter
    @Setter
    @Builder
    public static class Create{

        private String name;
        private String email;
        private String level;
        private String part;
        private Long projectId;
    }
}
