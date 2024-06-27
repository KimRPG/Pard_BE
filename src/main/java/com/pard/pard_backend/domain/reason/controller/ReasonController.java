package com.pard.pard_backend.domain.reason.controller;

import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.dto.response.ReasonResponseDTO;
import com.pard.pard_backend.domain.reason.entity.Reason;
import com.pard.pard_backend.domain.reason.service.ReasonService;
import com.pard.pard_backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reason")
public class ReasonController {
    private final ReasonService reasonService;
//    유저의 이메일로 점수 / 벌점 추가
   @PostMapping("")
   public void addPoint(@RequestBody ReasonRequest.ReasonRequestDTO req){
       reasonService.addPoint(req);
   }

//    유저의 이메일로 점수 수정

//    유저의 이메일로 점수 삭제
    @DeleteMapping("")
    public void deletePoint(@RequestBody ReasonRequest.ReasonDeleteDTO req){
        reasonService.deletePoint(req);
    }

//    유저의 이메일로 점수 조회 + 팡욱이 점수
    @GetMapping("/pardnership")
    public ReasonResponseDTO.UserPoint getPoint(@RequestParam String email){
        return reasonService.getPoint(email);
    }

//    유저의 파트,기수 내 점수
    @GetMapping("/part/my-rank")
    public ReasonResponseDTO.UserRank getPartPoint(@RequestParam String email){
        return reasonService.getRank(email);
    }

//    유저의 기수에 맞는 사람들의 이름,파트, 점수
    @GetMapping("/rank")
    public List<ReasonResponseDTO.RankInfo> getRank(@RequestParam String email){
        return reasonService.getRankListFromGeneration(email);
    }

//    top3의 이름,피트 조회


}
