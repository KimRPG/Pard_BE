package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public UserResponseDTO.UserInfo findByToken(String token) {
        return userService.findByEmail(jwtUtil.getEmail(token));
    }
}
