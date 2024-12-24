package com.pard.pard_backend.domain.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.pard.pard_backend.domain.fcm.dto.FCMNotificationRequestDto;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    // 얘는 하나만 보내는 거
    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {
        Optional<User> user = userRepository.findById(requestDto.getTargetUserId());

        if (user.isPresent()) {
            if (user.get().getFcmToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(user.get().getFcmToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전달했습니다. targetId = "
                            + requestDto.getTargetUserId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알람 보내기를 실패했습니다. targetId = "
                            + requestDto.getTargetUserId() + ", " + e.getMessage();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetId = "
                        + requestDto.getTargetUserId();
            }
        } else {
            return "해당 유저가 존재하지 않습니다. targetId = "
                    + requestDto.getTargetUserId();
        }
    }
}
