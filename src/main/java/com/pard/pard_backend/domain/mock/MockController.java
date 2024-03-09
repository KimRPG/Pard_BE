package com.pard.pard_backend.domain.mock;

import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.Role;
import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            2.3f, // 총 차감 금액
            13.0f, // 총 보너스 금액
            null
    );

    List<Schedule> scheduleList = new ArrayList<>();
    Schedule schedule = new Schedule(1L,
            "1차세미나",
            new Date(),
            "Meeting details",
            "WEB");
    Schedule schedule2 = new Schedule(1L,
            "2차세미나",
            new Date(),
            "Meeting details",
            "WEB");

    @GetMapping("/user/info")
    public UserResponseDTO.UserInfo getUserInfo() {
        return UserResponseDTO.UserInfo.toDto(a);
    }

    @GetMapping("/user/bonus")
    public UserResponseDTO.UserBonusPoint getUserBonus() {
        return UserResponseDTO.UserBonusPoint.toDto(a);
    }
    @GetMapping("/user/minus")
    public UserResponseDTO.UserMinusPoint getUserMinus() {
        return UserResponseDTO.UserMinusPoint.toDto(a);
    }
    @GetMapping("/schedule")
    public List<ScheduleResponseDTO> getPartSchedule(@RequestParam String part) {
        scheduleList.add(schedule); scheduleList.add(schedule2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase(part))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}

