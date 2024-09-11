package com.pard.pard_backend.domain.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Seminar {
    OT("OT"),
    SEMINAR_1("1차 세미나"),
    SEMINAR_2("2차 세미나"),
    SEMINAR_3("3차 세미나"),
    SEMINAR_4("4차 세미나"),
    SEMINAR_5("5차 세미나"),
    SEMINAR_6("6차 세미나"),
    UNION_SEMINAR_1("연합 세미나1"),
    UNION_SEMINAR_2("연합 세미나2"),
    IDEA_PITCH("아이디어 피칭"),
    FINAL_MEETING("종강총회"),
    LONGKERTON("롱커톤");
    private final String description;
}
