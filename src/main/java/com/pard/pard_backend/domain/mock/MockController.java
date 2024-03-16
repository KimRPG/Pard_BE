package com.pard.pard_backend.domain.mock;

import com.pard.pard_backend.domain.reason.dto.response.ReasonResponseDTO;
import com.pard.pard_backend.domain.reason.entity.Reason;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.Role;
import com.pard.pard_backend.domain.user.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mock")
public class MockController {
    User a = new User(
            1L, // 사용자 ID
            "Design", // 파트
            "김철수", // 이름
            Role.YB, // 역할
            "example@email.com", // 이메일
            "010-1234-5678", // 전화번호
            "3", // 세대
            "FCM_TOKEN", // FCM 토큰
            true, // 알람 설정 여부
            2.3f, // 총 차감 금액
            13.0f, // 총 보너스 금액
            null
    );
    User b = new User(
            2L, // 사용자 ID
            "WEB", // 파트
            "박짱구", // 이름
            Role.YB, // 역할
            "hu@email.com", // 이메일
            "010-1233-5678", // 전화번호
            "3", // 세대
            "FCM_TOKEN", // FCM 토큰
            true, // 알람 설정 여부
            2.3f, // 총 차감 금액
            11.0f, // 총 보너스 금액
            null
    );
    User c = new User(
            1L, // 사용자 ID
            "Server", // 파트
            "조훈이", // 이름
            Role.YB, // 역할
            "example@email.com", // 이메일
            "010-1234-5678", // 전화번호
            "3", // 세대
            "FCM_TOKEN", // FCM 토큰
            true, // 알람 설정 여부
            2.3f, // 총 차감 금액
            9.0f, // 총 보너스 금액
            null
    );
    User d = new User(
            1L, // 사용자 ID
            "PM", // 파트
            "최유리", // 이름
            Role.YB, // 역할
            "example@email.com", // 이메일
            "010-1234-5678", // 전화번호
            "3", // 세대
            "FCM_TOKEN", // FCM 토큰
            true, // 알람 설정 여부
            2.3f, // 총 차감 금액
            7.0f, // 총 보너스 금액
            null
    );
    Set<User> users = new HashSet<>();



    Schedule schedule = new Schedule(1L,
            "1차세미나",
            new Date(),
            "Meeting details",
            "WEB",
            "헤브론홀"
            );
    Schedule schedule2 = new Schedule(1L,
            "2차세미나",
            new Date(),
            "Meeting details",
            "WEB",
            "헤브론홀");
    Schedule scheduleAllPart = new Schedule(
            1L,
            "OT",
            new Date(),
            "3기 OT",
            "ALL",
            "헤브론홀"
    );

    Schedule scheduleAllPart2 = new Schedule(
            1L,
            "OT",
            new Date(),
            "3기 OT 아이스크림 사와라",
            "ALL",
            "헤브론홀"
    );
    Set<Schedule> scheduleList = new HashSet<>();
    Reason reason = new Reason(
            1L,
            -0.5f,
            false,
            1L,
            "과제 지각",
            "1,2,3차 세미나 지각",
            new Date()
    );
    Reason reason1 = new Reason(
            2L,
            -2f,
            false,
            1L,
            "세미나 결석",
            "1차 세미나 결석",
            new Date()
    );
    Set<Reason> reasons = new HashSet<>();
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
        scheduleList.add(schedule);
        scheduleList.add(schedule2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase(part))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));

    }

    //    *******************************여기부터 스케쥴 목업**********************************
//    파트 일정 중 D-Day 안지난거 가져오는 목업
    @GetMapping("/schedule/{part}/up-coming")
    public List<ScheduleResponseDTO> getPartScheduleUpcomming(@PathVariable String part) {
        scheduleList.add(schedule);
        scheduleList.add(schedule2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase(part))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //    파트 일정 중 D-Day 지난거 가져오는 목업
    @GetMapping("/schedule/{part}/passed")
    public List<ScheduleResponseDTO> getPartSchedulePassed(@PathVariable String part) {
        scheduleList.add(schedule);
        scheduleList.add(schedule2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase(part))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //    파트에 "전체"라고 저장되어 있는 스케쥴 중 D-Day 안지난거 가져오는 목업
    @GetMapping("/schedule/all/up-coming")
    public List<ScheduleResponseDTO> getCommonSchedule() {
        scheduleList.add(scheduleAllPart);
        scheduleList.add(scheduleAllPart2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase("ALL"))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //    파트에 "전체"라고 저장되어 있는 스케쥴 중 D-Day 지난거 가져오는 목업
    @GetMapping("/schedule/all/passed")
    public List<ScheduleResponseDTO> getCommonSchedulePassed() {
        scheduleList.add(scheduleAllPart);
        scheduleList.add(scheduleAllPart2);
        return scheduleList.stream()
                .filter(schedule -> schedule.getPart().equalsIgnoreCase("ALL"))
                .map(ScheduleResponseDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
//    *******************************여기까지 스케쥴 목업**********************************

    @GetMapping("/user/{generation}/top-three")
    public List<UserResponseDTO.UserTopThree> getUserGenerationTopThree(@PathVariable String generation) {
        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        return users.stream()
                .filter(user -> user.getGeneration().equalsIgnoreCase(generation))
                .limit(3)
                .map(UserResponseDTO.UserTopThree::toDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/user/{generation}/all")
    public List<UserResponseDTO.UserGenerationAll> getUserGenerationAll(@PathVariable String generation) {
        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        return users.stream()
                .filter(user -> user.getGeneration().equalsIgnoreCase(generation))
                .map(UserResponseDTO.UserGenerationAll::toDto)
                .sorted(Comparator.comparing(UserResponseDTO.UserGenerationAll::getTotalBonus).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/reason/minus")
    public List<ReasonResponseDTO.ReasonMinus> getReasonMinus(@RequestParam(value="id") Long userId) {
        reasons.add(reason);
        reasons.add(reason1);
        return reasons.stream()
                .filter(reason -> !reason.isBonus() && reason.getUserId() == userId)
                .map(ReasonResponseDTO.ReasonMinus::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/reason/bonus")
    public List<ReasonResponseDTO.ReasonMinus> getReasonBonus(@RequestParam(value="id") Long userId) {
        reasons.add(reason);
        reasons.add(reason1);
        return reasons.stream()
                .filter(reason -> reason.isBonus() && reason.getUserId() == userId)
                .map(ReasonResponseDTO.ReasonMinus::toDto)
                .collect(Collectors.toList());
    }
}

