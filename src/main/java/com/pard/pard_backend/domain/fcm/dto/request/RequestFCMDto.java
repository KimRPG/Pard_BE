package com.pard.pard_backend.domain.fcm.dto.request;

import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RequestFCMDto {
    @Builder
    @Getter
    public static class FCMRequestDTO {
        private boolean validate_only;
        private Message message;
    }

//    알림 보내는 메세지 dto
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private Notification notification; // 모든 mobile os를 아우를수 있는 Notification
        private String token; // 특정 device에 알림을 보내기위해 사용
    }

//    알림 내용 dto
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }

}
