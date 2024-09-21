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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReasonService {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final ReasonRepository reasonRepository;

    @Transactional
    public void addPoint(ReasonRequest.ReasonRequestDTO req) {
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        if (req.isBonus()) {
            user.setTotalBonus(user.getTotalBonus() + (int)req.getPoint());
        } else {
            user.setTotalMinus(user.getTotalMinus() + req.getPoint());
        }
        Reason reason = new Reason().toEntity(req, user);
        reasonRepository.save(reason);
//        userRepository.save(user);
    }

    @Transactional
    public void deletePoint(ReasonRequest.ReasonDeleteDTO req) {
        Reason reason = reasonRepository.findById(req.getReasonId())
                .orElseThrow(()-> new ProjectException.ReasonNotFound(ProjectErrorCode.REASON_NOT_FOUND));
        User user = reason.getUser();
        if (reason.isBonus()) {
            user.setTotalBonus(user.getTotalBonus() - (int)reason.getPoint());
        } else {
            user.setTotalMinus(user.getTotalMinus() - reason.getPoint());
        }
        reasonRepository.deleteById(req.getReasonId());
        userRepository.save(user);
    }

//    @Transactional(readOnly = true)
//    public ReasonResponseDTO.UserPoint getPoint(String token) {
//        User user = userRepository.findByEmail(jwtUtil.getEmail(token)).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
//        return ReasonResponseDTO.UserPoint.toDto(user);
//    }

    public List<ReasonResponseDTO.ReasonDTO> getReason(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        return reasonRepository.findByUser(user)
                .stream()
                .map(ReasonResponseDTO.ReasonDTO::toDto)
                .collect(Collectors.toList());

    }

}
