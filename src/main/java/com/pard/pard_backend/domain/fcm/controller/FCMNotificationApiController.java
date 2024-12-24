package com.pard.pard_backend.domain.fcm.controller;

import com.pard.pard_backend.domain.fcm.dto.FCMNotificationRequestDto;
import com.pard.pard_backend.domain.fcm.service.FCMNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notification")
public class FCMNotificatoinApiController {
    private final FCMNotificationService fcmNotificationService;

    @Operation(summary = "FCM 알림")
    @PostMapping("")
    public ResponseEntity<?> sendNotificationByToken(@RequestBody FCMNotificationRequestDto requestDto) {
        return ResponseEntity.ok(fcmNotificationService.sendNotificationByToken(requestDto));
    }
}
