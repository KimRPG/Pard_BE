package com.pard.pard_backend.domain.fcm.controller;

import com.pard.pard_backend.domain.fcm.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fcm")
@RequiredArgsConstructor
public class FCMController {
    private final FCMService fcmService;
    @Value("${firebase.project-id}")
    String projectId;
    private String API_URL = "https://fcm.googleapis.com/v1/projects/"+projectId+"/messages:send";


}
