package com.pard.pard_backend.domain.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    GUEST("ROLE_GUEST"),
    YB("ROLE_YB"),
    OB("ROLE_OB"),
    ADMIN("ROLE_ADMIN");

    private final String key;
    Role(String key) {
        this.key = key;
    }
}
