package com.pard.pard_backend.domain.ranking.service;

import com.pard.pard_backend.domain.ranking.dto.response.RankingResponseDTO;
import com.pard.pard_backend.domain.ranking.dto.response.Top3RankingDTO;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.dto.request.UserTESTDTO;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final UserRepository userRepository;
    private final UserJDBC userJDBC;
    public List<RankingResponseDTO> ranking(Integer generation) {

        return userJDBC.checkUsersByGeneration(generation);
    }

    public List<RankingResponseDTO> top3Rank(Integer generation) {
        return userJDBC.top3Ranking(generation);
    }

    public UserTESTDTO checkRank(String token) {
        return userJDBC.check(token);
    }
}
