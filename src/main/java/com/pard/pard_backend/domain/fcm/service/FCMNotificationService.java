package com.pard.pard_backend.domain.fcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.pard.pard_backend.domain.fcm.dto.FCMNotificationRequestDto;
import com.pard.pard_backend.domain.fcm.dto.FcmMessage;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Service
public class FCMNotificationService {
//    private final FirebaseMessaging firebaseMessaging;
//    private final UserRepository userRepository;

    // 얘는 하나만 보내는 거
//    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {
//        Optional<User> user = userRepository.findById(requestDto.getTargetUserId());
//
//        if (user.isPresent()) {
//            if (user.get().getFcmToken() != null) {
//                Notification notification = Notification.builder()
//                        .setTitle(requestDto.getTitle())
//                        .setBody(requestDto.getBody())
//                        .build();
//
//                Message message = Message.builder()
//                        .setToken(user.get().getFcmToken())
//                        .setNotification(notification)
//                        .build();
//
//                try {
//                    firebaseMessaging.send(message);
//                    return "알림을 성공적으로 전달했습니다. targetId = "
//                            + requestDto.getTargetUserId();
//                } catch (FirebaseMessagingException e) {
//                    e.printStackTrace();
//                    return "알람 보내기를 실패했습니다. targetId = "
//                            + requestDto.getTargetUserId() + ", " + e.getMessage();
//                }
//            } else {
//                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetId = "
//                        + requestDto.getTargetUserId();
//            }
//        } else {
//            return "해당 유저가 존재하지 않습니다. targetId = "
//                    + requestDto.getTargetUserId();
//        }
//    }


    private final String API_URL = "https://fcm.googleapis.com/v1/projects/pard-app-project/messages:send";
    private final ObjectMapper objectMapper;
    @Value("${firebase.config.path}")
    private String fuckingpath;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = fuckingpath;

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    // 아래는 매일 8시에 스케쥴을 확인하고 fcm으로 알람을 보내는 코드임.
    @Scheduled(cron = "0 7 16 * * *")
    private void sendNotification() throws IOException{
        List<String> reqlist = new ArrayList<>();
        reqlist.add("eyBvXg-AQBCdTiXmU79yA6:APA91bHlDrUX6Zf3VL60ImcKlLfsElc6K9X5gVUUEJn3akIOcTpjrreuk-50sb1qLscO3JRVEgghYXh11E-_85BwMvujLkuUTP3CPejosbqEu2u-WicNtKo");
        reqlist.add("eyBvXg-AQBCdTiXmU79yA6:APA91bHlDrUX6Zf3VL60ImcKlLfsElc6K9X5gVUUEJn3akIOcTpjrreuk-50sb1qLscO3JRVEgghYXh11E-_85BwMvujLkuUTP3CPejosbqEu2u-WicNtKo");

        sendMessageTo("eyBvXg-AQBCdTiXmU79yA6:APA91bHlDrUX6Zf3VL60ImcKlLfsElc6K9X5gVUUEJn3akIOcTpjrreuk-50sb1qLscO3JRVEgghYXh11E-_85BwMvujLkuUTP3CPejosbqEu2u-WicNtKo", "야", "되냐?");
        sendMessageTo("eyBvXg-AQBCdTiXmU79yA6:APA91bHlDrUX6Zf3VL60ImcKlLfsElc6K9X5gVUUEJn3akIOcTpjrreuk-50sb1qLscO3JRVEgghYXh11E-_85BwMvujLkuUTP3CPejosbqEu2u-WicNtKo", "야2", "되냐?2");


    }
}
