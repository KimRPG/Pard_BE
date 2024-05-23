package com.pard.pard_backend.domain.schedule.dto.request;

import com.google.api.client.util.DateTime;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private String title;
    private String content;
    private String part;
    private LocalDateTime date;
    private String contentsLocation;
    private boolean notice;
    @Nullable
    private Integer remaingDay;
    @Nullable
    private boolean isPastEvent;
}
