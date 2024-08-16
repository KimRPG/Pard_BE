package com.pard.pard_backend.domain.ranking.controller;

import com.pard.pard_backend.domain.ranking.service.RankingService;
import com.pard.pard_backend.domain.ranking.dto.response.RankingResponseDTO;
import com.pard.pard_backend.domain.ranking.dto.response.Top3RankingDTO;
import com.pard.pard_backend.domain.user.dto.request.UserTESTDTO;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/rank")
@RequiredArgsConstructor
@RestController
public class RankingController {
    private final RankingService rankingService;


    @GetMapping("/me")
    @Operation(summary = "해당 유저의 랭킹 정보를 가져옵니다.", description = "토큰을 입력하면 해당 토큰의 사용자의 랭킹 정보를 반환해줍니다.")
    public UserTESTDTO getRank(@CookieValue(value = "Authorization") String token){
        return rankingService.checkRank(token);
    }

    @GetMapping("/total")
    @Operation(summary = "모든 사용자의 랭킹 정보를 가져옵니다.", description = "이름과, 파트, 총 보너스의 정보를 반환해줍니다.")
    public ResponseEntity<List<RankingResponseDTO>> totalRank(@RequestParam Integer generation) {
        return ResponseEntity.ok().body(rankingService.ranking(generation));
    }

    @GetMapping("/top3")
    @Operation(summary = "탑 3 랭킹의 사용자 정보를 가져옵니다.", description = "이름과, 파트 정보를 반환해줍니다.")
    public ResponseEntity<List<RankingResponseDTO>> top3Rank(@RequestParam Integer generation) {
        return ResponseEntity.ok().body(rankingService.top3Rank(generation));
    }
}
