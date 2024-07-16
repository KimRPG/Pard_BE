package com.pard.pard_backend.domain.schedule.controller;

import com.pard.pard_backend.domain.schedule.dto.request.ScheduleRequest;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;


    @GetMapping("")
    @Operation(summary = "모든 스케쥴 반환", description = "저장되어 있는 모든 스케쥴의 정보를 출력해줍니다.")
    public ResponseEntity<List<ScheduleResponseDTO>> readSchedule(){
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @PostMapping("")
    @Operation(summary = "스케쥴 추가",description = "스케쥴 정보를 입력하면 해당 스케쥴을 저장해줍니다.")
    public void addSchedule(@RequestBody ScheduleRequest req){
        scheduleService.addSchedule(req);
    }

    @GetMapping("/{part}")
    @Operation(summary = "파트별 스케쥴 반환", description = "String 형식의 파트 이름을 입력 시, 해당 파트의 모든 스케쥴을 반환합니다.")
    public List<ScheduleResponseDTO> readPartSchedule(@PathVariable String part){
        return scheduleService.getPartSchedule(part);
    }

    @PatchMapping("/{scheduleId}")
    @Operation(summary = "스케쥴 수정", description = "수정하고자 하는 스케쥴의 id를 PathVariable로 넘기고, body로 수정할 정보를 넘기면 해당 id의 스케쥴을 수정해줍니다.")
    public void updateSchedule(@RequestBody ScheduleRequest req, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(req,scheduleId);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "스케쥴 삭제", description = "삭제하고자 하는 스케쥴의 id를 PathVariable로 넘기면 해당 스케쥴을 삭제합니다.")
    public void deleteSchedule(@PathVariable Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
    }
}
