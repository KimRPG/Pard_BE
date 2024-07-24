package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.cookie.service.CookieService;
import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final CookieService cookieService;

    public UserResponseDTO.UserInfo findByToken(String token) {
        return userService.findByEmail(jwtUtil.getEmail(token));
    }

    public String login(UserRequestDTO.Login dto, HttpServletResponse response) throws ProjectException.UserNotFoundException {
        String email = dto.getEmail();
        UserResponseDTO.UserInfo userInfo = userService.login(email, response);
        String token = jwtUtil.createJwt(userInfo.getName(), userInfo.getRole(), email);
        response.addCookie(cookieService.createCookie("Authorization", token));
        return token;
    }

    public void deleteByToken(String token, HttpServletResponse response) {
        cookieService.clearJwtCookie(response);
        String email = jwtUtil.getEmail(token);
        userService.deleteUser(email);
    }

}
