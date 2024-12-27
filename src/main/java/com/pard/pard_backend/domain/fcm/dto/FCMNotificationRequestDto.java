package com.pard.pard_backend.domain.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetUserId;
    private String targetToken;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequestDto(Long targetUserId, String title, String body, String targetToken) {
        this.targetUserId = targetUserId;
        this.targetToken = targetToken;
        this.title = title;
        this.body = body;
    }

}
