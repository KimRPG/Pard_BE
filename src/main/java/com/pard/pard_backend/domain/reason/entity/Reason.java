package com.pard.pard_backend.domain.reason.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reasonId;

    private float point;

    private boolean isBonus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    private String reason;

    private String detail;

    @CreationTimestamp
    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private Date createDate;

    @ColumnDefault("false")
    private boolean attendance;

    public Reason toEntity(ReasonRequest.ReasonRequestDTO req) {
        this.point = req.getPoint();
        this.isBonus = req.isBonus();
        this.reason = req.getReason();
        this.detail = req.getDetail();
        this.attendance = req.isAttendance();
        return this;
    }

}
