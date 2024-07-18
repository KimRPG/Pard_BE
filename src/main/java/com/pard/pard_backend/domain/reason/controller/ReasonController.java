package com.pard.pard_backend.domain.reason.controller;

import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.dto.response.ReasonResponseDTO;
import com.pard.pard_backend.domain.reason.service.ReasonService;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reason")
public class ReasonController {
    private final ReasonService reasonService;
    private final JWTUtil jwtUtil;

//    유저의 이메일로 점수 / 벌점 추가
   @PostMapping("")
   @Operation(summary = "해당 이메일의 유저의 상, 벌점을 추가해줍니다.", description = "상, 벌점을 입력한 값으로 수정이 아닌, 입력한 값 만큼 더해줍니다.")
   public void addPoint(@RequestBody ReasonRequest.ReasonRequestDTO req){
       reasonService.addPoint(req);
   }

//    유저의 이메일로 점수 수정

//    유저의 이메일로 점수 삭제
    @DeleteMapping("")
    @Operation(summary = "해당 아이디의 상, 벌점을 삭제합니다.", description = "점수 삭제")
    public void deletePoint(@RequestBody ReasonRequest.ReasonDeleteDTO req){
        reasonService.deletePoint(req);
    }

//    유저의 이메일로 점수 조회 + 팡욱이 점수
    @GetMapping("")
    @Operation(summary = "토큰의 사용자의 점수를 조회합니다.", description = "입력한 토큰의 사용자를 검색하고, 해당 사용자의 점수를 조회해 줍니다.")
    public List<ReasonResponseDTO.ReasonDTO> getPoint(@CookieValue(value = "Authorization") String token){
        return reasonService.getReason(jwtUtil.getEmail(token));
    }

    @GetMapping("/admin")
    @Operation(summary = "입력한 이메일의 사용자의 점수를 조회합니다.", description = "입력한 사용자의 점수와 이유 등을 반환해줍니다.")
    public List<ReasonResponseDTO.ReasonDTO> getUserPoint(@RequestParam String email) {
        return reasonService.getReason(email);
    }


//    유저의 파트,기수 내 점수
//    @GetMapping("/part/my-rank")
//    public ReasonResponseDTO.UserRank getPartPoint(@CookieValue(value = "Authorization") String token){
//        return reasonService.getRank(token);
//    }

//    유저의 기수에 맞는 사람들의 이름,파트, 점수


//    top3의 이름,피트 조회


}
