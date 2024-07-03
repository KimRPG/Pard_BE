package com.pard.pard_backend.domain.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pard.pard_backend.domain.attendance.dto.AttendanceAdminRequestDTO;
import com.pard.pard_backend.domain.attendance.dto.AttendanceRequestDto;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "seminar"})
})
//접근시 불가능이라고 띄우기
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Setter
    private AttendanceStatus status;

    @Enumerated(EnumType.STRING)
    @Setter
    private Seminar seminar;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public static Attendance toEntity(AttendanceRequestDto request, User user) {

        return Attendance.builder()
                .seminar(request.getSeminar())
                .status(request.getStatus())
                .user(user)
                .build();
    }

    public static Attendance toEntity(AttendanceAdminRequestDTO request, User user) {

        return Attendance.builder()
                .seminar(request.getSeminar())
                .status(request.getStatus())
                .user(user)
                .build();
    }
}
