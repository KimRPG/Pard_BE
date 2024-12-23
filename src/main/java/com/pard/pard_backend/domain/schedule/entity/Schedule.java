package com.pard.pard_backend.domain.schedule.entity;

import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.schedule.dto.request.ScheduleRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleId;

    private String title;

    private LocalDateTime date;

    private String content;

    private String part;

    private String contentsLocation;

    private boolean notice;

    private boolean isPastEvent;

    private long generation;

    public static Schedule from(ScheduleRequest req){
        return Schedule.builder()
                .title(req.getTitle())
                .date(req.getDate())
                .content(req.getContent())
                .part(req.getPart())
                .contentsLocation(req.getContentsLocation())
                .notice(req.isNotice())
                .generation(req.getGeneration())
                .build();
    }
}
