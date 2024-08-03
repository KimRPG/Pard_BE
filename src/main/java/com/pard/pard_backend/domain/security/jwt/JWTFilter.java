package com.pard.pard_backend.domain.security.jwt;

import com.pard.pard_backend.domain.cookie.service.CookieService;
import com.pard.pard_backend.domain.security.dto.CustomUserDetails;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CookieService cookieService;

    public JWTFilter(JWTUtil jwtUtil, CookieService cookieService) {
        this.jwtUtil = jwtUtil;
        this.cookieService = cookieService;
    }

    /*손 좀 봐야함*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //여기다가 cache-control 하는 response 만들어 버림 배포하고 안되면 다시 설정
//        response.setHeader("Cache-Control","max-age=86400, must-revalidate");
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> authorization = Arrays.stream(cookies)
                .filter(cookie -> "Authorization".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();

        if (authorization.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.get();

        try {
            if (jwtUtil.getExpired(token)) {
                throw new ExpiredJwtException(null, null, "Token expired");
            }

            String name = jwtUtil.getName(token);
            String email = jwtUtil.getEmail(token);
            String role = jwtUtil.getRole(token);

            UserRequestDTO.Jwt userDTO = new UserRequestDTO.Jwt();
            userDTO.setName(name);
            userDTO.setEmail(email);
            userDTO.setRole(role);

            CustomUserDetails customOAuth2User = new CustomUserDetails(userDTO);
            System.out.println(customOAuth2User.getAuthorities());
            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e.getMessage());
            cookieService.clearJwtCookie(response);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired");
            return;
        }

        filterChain.doFilter(request, response);
    }


}
