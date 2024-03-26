package com.pard.pard_backend.domain.fcm.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component //서버 시작전에 돌려야 함으로 bean으로 등록
public class FcmInitializer {
    @Value("${firebase.key-path}")
    String keyPath;

    @PostConstruct //WAS 구동시 bean 생성될 때 초기화
    public void getFcmCredential(){
        try {
            InputStream refreshToken = new ClassPathResource(keyPath).getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

            FirebaseApp.initializeApp(options);
            log.info("Fcm Setting Completed");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
