package com.pard.pard_backend.domain.fcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.pard.pard_backend.domain.fcm.dto.request.RequestFCMDto;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService {

    @Value("${firebase.key-path}")
    String keyPath;

    @Value("${firebase.project-id}")
    String projectId;
    private String API_URL = "https://fcm.googleapis.com/v1/projects/"+projectId+"/messages:send";

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

//    파베의 AccessToken으로 FCM에 푸시 요청 보낼 때 Header에 사용 
    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(keyPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

//    FCM 전송 로직 : User DB에서 token 가져와서 List로 만들고 FCM에 전송
    public List<String> getFCMTokenList() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(User::getFcmToken)
                .toList();
    }

    //    단체 발송하는 fcm 메서드
    public void sendPushs(RequestFCMDto.Notification req) throws FirebaseMessagingException{
        List<String> targetUserTokens = getFCMTokenList();
        FirebaseMessaging.getInstance().sendEachForMulticast(makeMessages(req.getTitle(), req.getBody(), targetUserTokens));
    }

//    단체 message 만들기
    public static MulticastMessage makeMessages(String title, String body,List<String> targetUserTokens){
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        return MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(targetUserTokens)
                .build();
    }

//    기기의 token으로 메세지 만드는 메서드
//    private String makeMessage(String token, String title,String body) throws JsonProcessingException {
//        RequestFCMDto.FCMRequestDTO fcmMessage = RequestFCMDto.FCMRequestDTO.builder()
//                .validate_only(false)
//                .message(RequestFCMDto.Message.builder()
//                        .notification(RequestFCMDto.Notification.builder()
//                                .title(title)
//                                .body(body)
//                                .build())
//                        .token(token)
//                        .build())
//                .build();
//        return objectMapper.writeValueAsString(fcmMessage);
//    }

//    notification push 보내는 역할하는 method
//    public void sendMessageTo(String token, String title, String body) throws IOException {
//        String message = makeMessage(token, title, body);
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
//        Request request = new Request.Builder()
//                .url(API_URL)
//                .post(requestBody)
//                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
//                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        log.info(response.body().string());
//    }
}
