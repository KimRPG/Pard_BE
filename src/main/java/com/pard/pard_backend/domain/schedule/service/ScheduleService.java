package com.pard.pard_backend.domain.schedule.service;

import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.schedule.dto.request.ScheduleRequest;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.schedule.repo.ScheduleRepo;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;

    LocalDate today = LocalDate.now();


//    Schedule 추가
    public void addSchedule(ScheduleRequest req){
        scheduleRepo.save(Schedule.from(req));
    }

//    Schedule 수정
    public void updateSchedule(ScheduleRequest req,Long scheduleId){
        Optional<Schedule> s = scheduleRepo.findById(scheduleId);
        if(s.isEmpty()){
            throw new ProjectException.ScheduleNotFound(ProjectErrorCode.ScheduleNotFound);
        }
        Schedule schedule = s.get();
        if(req.getTitle()!=null){schedule.setTitle(req.getTitle());}
        if(req.getContent()!=null){schedule.setContent(req.getContent());}
        if(req.getPart()!=null){schedule.setPart(req.getPart());}
        if(req.getContentsLocation()!=null){schedule.setContentsLocation(req.getContentsLocation());}
        if(req.getDate()!=null){schedule.setDate(req.getDate());}
        schedule.setNotice(req.isNotice());
        scheduleRepo.save(schedule);
    }

//    Schedule 삭제
    public void deleteSchedule(Long scheduleId){
        Optional<Schedule> s = scheduleRepo.findById(scheduleId);
        if(s.isEmpty()){
            throw new ProjectException.ScheduleNotFound(ProjectErrorCode.ScheduleNotFound);
        }
        scheduleRepo.deleteById(scheduleId);
    }

//    Schedule 전체
    public List<ScheduleResponseDTO> getAllSchedule(){
        List<Schedule> schedules = scheduleRepo.findAll();
        List<ScheduleResponseDTO> ret = new ArrayList<>();
        for (Schedule schedule : schedules) {
            Date d = Date.from(schedule.getDate().atZone(ZoneId.systemDefault()).toInstant());
            LocalDate scheduleDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int remainingDay = (int) ChronoUnit.DAYS.between(today, scheduleDate);
            if (remainingDay < 0) {
                schedule.setPastEvent(true);
            }
            schedule.setRemaingDay(remainingDay);
            ret.add(new ScheduleResponseDTO(schedule));
        }
        return ret;
    }

//    Schedule 파트별 조회
    public List<ScheduleResponseDTO> getPartSchedule(String part){
        Optional<List<ScheduleResponseDTO>> schedules = scheduleRepo.findAllByPart(part);
        if(schedules.isEmpty()){
            throw new ProjectException.ScheduleNotFound(ProjectErrorCode.ScheduleNotFound);
        }
        return schedules.get();
    }

//    Schedule 오늘치 시간 조회(QR에 쓰는거)
    public Timestamp getTodayQRTime(){
        Optional<List<Schedule>> s = scheduleRepo.findByDateAndNoticeIsTrue(today);
        if(s.isEmpty()){
            throw new ProjectException.ScheduleNotFound(ProjectErrorCode.ScheduleNotFound);
        }
        List<Schedule> schedules = s.get();
        Schedule todaySchedule = schedules.get(0);
        LocalDateTime date= todaySchedule.getDate();
        return Timestamp.valueOf(date);
    }
}
