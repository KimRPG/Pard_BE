package com.pard.pard_backend.domain.ranking.controller;

import com.pard.pard_backend.domain.ranking.service.RankingService;
import com.pard.pard_backend.domain.ranking.dto.response.RankingResponseDTO;
import com.pard.pard_backend.domain.ranking.dto.response.Top3RankingDTO;
import com.pard.pard_backend.domain.user.dto.request.UserTESTDTO;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/rank")
@RequiredArgsConstructor
@RestController
public class RankingController {
    private final RankingService rankingService;
    private final UserJDBC userJDBC;

    @GetMapping("/me")
    public UserTESTDTO getRank(@CookieValue(value = "Authorization") String token){
        return userJDBC.check(token);
    }

    @GetMapping("/total")
    public ResponseEntity<List<RankingResponseDTO>> totalRank() {
        return ResponseEntity.ok().body(rankingService.ranking());
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Top3RankingDTO>> top3Rank() {
        return ResponseEntity.ok().body(rankingService.top3Rank());
    }
}
