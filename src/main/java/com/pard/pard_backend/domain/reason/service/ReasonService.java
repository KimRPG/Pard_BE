package com.pard.pard_backend.domain.reason.service;

import com.pard.pard_backend.domain.reason.dto.request.ReasonRequest;
import com.pard.pard_backend.domain.reason.dto.response.ReasonResponseDTO;
import com.pard.pard_backend.domain.reason.entity.Reason;
import com.pard.pard_backend.domain.reason.repository.ReasonRepository;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReasonService {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final ReasonRepository reasonRepository;

    @Transactional
    public void addPoint(ReasonRequest.ReasonRequestDTO req) {
        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
        if (req.isBonus()) {
            user.setTotalBonus(user.getTotalBonus() + req.getPoint());
        } else {
            user.setTotalMinus(user.getTotalMinus() + req.getPoint());
        }
        Reason reason = new Reason().toEntity(req);
        reasonRepository.save(reason);
        userRepository.save(user);
    }

    @Transactional
    public void addSchedulePoint(ReasonRequest.SchedulePointDTO req) {
        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
        user.setPangoolPoint(user.getPangoolPoint() + req.getPoint());
    }

    @Transactional
    public void deletePoint(ReasonRequest.ReasonDeleteDTO req) {
        Optional<Reason> r = reasonRepository.findById(req.getReasonId());
        if (r.isEmpty()) {throw new ProjectException.ReasonNotFound(ProjectErrorCode.REASON_NOT_FOUND);}
        Reason reason = r.get();
        User user = userRepository.findByEmail(req.getEmail());
        if (reason.isBonus()) {
            user.setTotalBonus(user.getTotalBonus() - reason.getPoint());
        } else {
            user.setTotalMinus(user.getTotalMinus() - reason.getPoint());
        }
        reasonRepository.deleteById(req.getReasonId());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ReasonResponseDTO.UserPoint getPoint(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
        return ReasonResponseDTO.UserPoint.toDto(user);
}

    public int findRankInPart(String email,String part) {
        List<User> users = userRepository.findUsersByPartOrderedByTotalBonus(part);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return i + 1;
            }
        }
        return -1;
    }
    public int findRankInTotal(String email,String generation) {
        List<User> users = userRepository.findUsersByGenerationOrderedByTotalBonus(generation);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return i + 1;
            }
        }
        return -1;
    }

    public ReasonResponseDTO.UserRank getRank(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
        return ReasonResponseDTO.UserRank.builder()
                .partRanking(findRankInPart(email,user.getPart()))
                .totalRanking(findRankInTotal(email, user.getGeneration()))
                .build();
    }

    public List<ReasonResponseDTO.RankInfo> getRankListFromGeneration(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {throw new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND);}
        List<User> users = userRepository.findUsersByGenerationOrderedByTotalBonus(user.getGeneration());
        List<ReasonResponseDTO.RankInfo> ret = new ArrayList<>();
        for(int i = 0; i<users.size(); i++){
            ReasonResponseDTO.RankInfo rankInfo = ReasonResponseDTO.RankInfo.builder()
                    .rank(i+1)
                    .name(users.get(i).getName())
                    .part(users.get(i).getPart())
                    .totalBonusPoint(users.get(i).getTotalBonus())
                    .build();
            ret.add(rankInfo);
        }
        return ret;
    }
}
