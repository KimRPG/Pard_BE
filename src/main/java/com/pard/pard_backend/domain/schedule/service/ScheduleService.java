package com.pard.pard_backend.domain.schedule.service;

import com.google.api.client.util.DateTime;
import com.pard.pard_backend.domain.schedule.dto.request.ScheduleRequest;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.schedule.repo.ScheduleRepo;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.stream.Collectors;

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

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul") //한국시간 기준 매일 00:00에 실행됨
    public void markPastEvents() {
        scheduleRepo.markPastEvents();
    }


//    Schedule 전체
    @Transactional
    public List<ScheduleResponseDTO> getAllSchedule(){
        List<Schedule> pastSchedules = scheduleRepo.findPastSchedulesOrderByDate(); //일정 지나고 최신가 제일 가까운 순
        List<Schedule> commingSchedules = scheduleRepo.findActiveSchedules(); //일정 안지난 것들

//        List<Schedule> schedules = scheduleRepo.findAll();
        List<ScheduleResponseDTO> ret = new ArrayList<>();

        for (Schedule schedule : commingSchedules) {
            Integer remainingDay = remainDate(schedule.getDate());
            ret.add(new ScheduleResponseDTO(schedule, remainingDay-1));
        }

        ret.addAll(
                pastSchedules.stream()
                        .map(schedule -> new ScheduleResponseDTO(schedule, 0))
                        .toList()
        );

        return ret;
    }

//    Schedule 파트별 조회
    @Transactional
    public List<ScheduleResponseDTO> getPartSchedule(String part){
        List<Schedule> schedules = scheduleRepo.findAllByPart(part).get();
        if(schedules.isEmpty()){
            throw new ProjectException.ScheduleNotFound(ProjectErrorCode.ScheduleNotFound);
        }
        return schedules.stream()
                .map(schedule -> {
                    Integer remainDate= remainDate(schedule.getDate());
                    if(!schedule.isPastEvent()) {
                        if (remainDate < 0) {
                            schedule.setPastEvent(true);
                        }
                    }
                    return new ScheduleResponseDTO(schedule, remainDate-1);
                })
                .collect(Collectors.toList());
    }


    public Integer remainDate(LocalDateTime date) {
        Date d = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        LocalDate scheduleDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.DAYS.between(today, scheduleDate);
    }
}
