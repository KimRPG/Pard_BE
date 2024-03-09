package com.pard.pard_backend.domain.mock;

import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.Role;
import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {
    User a = new User(
            1L, // 사용자 ID
            "디자인", // 파트
            "김철수", // 이름
            Role.YB, // 역할
            "example@email.com", // 이메일
            "010-1234-5678", // 전화번호
            "2023", // 세대
            "FCM_TOKEN", // FCM 토큰
            true, // 알람 설정 여부
            0f, // 총 차감 금액
            0f, // 총 보너스 금액
            null
    );

    @GetMapping("/user/info")
    public UserResponseDTO.UserInfo getUserInfo() {
        return UserResponseDTO.UserInfo.toDto(a);
    }

}
