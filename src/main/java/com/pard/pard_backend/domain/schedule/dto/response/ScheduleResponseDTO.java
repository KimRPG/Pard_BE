package com.pard.pard_backend.domain.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ScheduleResponseDTO {
    private long scheduleId;
    private String title;
    private LocalDateTime date;
    private String content;
    private String part;
    private String contentsLocation;
    private boolean notice;
    private Integer remaingDay;
    @JsonProperty("isPastEvent")
    private boolean isPastEvent;
    private long generation;

    public ScheduleResponseDTO(final @NotNull Schedule schedule, Integer remaingDay) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.date = schedule.getDate();
        this.content = schedule.getContent();
        this.part = schedule.getPart();
        this.contentsLocation = schedule.getContentsLocation();
        this.notice = schedule.isNotice();
        this.remaingDay = remaingDay;
        this.isPastEvent = schedule.isPastEvent();
        this.generation = schedule.getGeneration();
    }



}
