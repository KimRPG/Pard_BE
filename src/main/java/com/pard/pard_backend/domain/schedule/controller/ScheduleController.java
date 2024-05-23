package com.pard.pard_backend.domain.schedule.controller;

import com.pard.pard_backend.domain.schedule.dto.request.ScheduleRequest;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;


    @GetMapping("")
    public ResponseEntity<List<ScheduleResponseDTO>> readSchedule(){
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @PostMapping("")
    public void addSchedule(@RequestBody ScheduleRequest req){
        scheduleService.addSchedule(req);
    }

    @GetMapping("/{part}")
    public List<ScheduleResponseDTO> readPartSchedule(@PathVariable String part){
        return scheduleService.getPartSchedule(part);
    }

    @PatchMapping("/{scheduleId}")
    public void updateSchedule(@RequestBody ScheduleRequest req, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(req,scheduleId);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
    }
}
