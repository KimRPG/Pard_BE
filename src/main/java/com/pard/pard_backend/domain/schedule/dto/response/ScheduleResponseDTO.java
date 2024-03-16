package com.pard.pard_backend.domain.schedule.dto.response;

import com.pard.pard_backend.domain.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Getter
@Setter
public class ScheduleResponseDTO {
    private String title;
    private Date scheduleDate;
    private String content;
    private String part;
    private String place;

    public ScheduleResponseDTO(final @NotNull Schedule schedule) {
        this.title = schedule.getTitle();
        this.scheduleDate = schedule.getScheduleDate();
        this.content = schedule.getContent();
        this.part = schedule.getPart();
        this.place = schedule.getPlace();
    }


}
