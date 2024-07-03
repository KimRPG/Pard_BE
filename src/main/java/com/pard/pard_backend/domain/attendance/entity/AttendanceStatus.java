package com.pard.pard_backend.domain.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttendanceStatus {
    출석(6f),  // 출석
    지각(4f),     // 지각
    결석(0f);    // 결석

    private final Float point;
}