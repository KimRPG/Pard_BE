package com.pard.pard_backend.domain.security.jwt;

import com.pard.pard_backend.domain.security.dto.CustomOAuth2User;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
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

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        Cookie[] cookies = request.getCookies();
        String authorization = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("Authorization"))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestUri = request.getRequestURI();

        if (requestUri.matches("^/login(?:/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^/oauth2(?:/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        if (jwtUtil.getExpired(authorization)) {
            System.out.println("token Expired");
            filterChain.doFilter(request, response);
            return;
        }


        String name = jwtUtil.getName(authorization);
        String email = jwtUtil.getEmail(authorization);
        String role = jwtUtil.getRole(authorization);

        UserRequestDTO.Jwt userDTO = new UserRequestDTO.Jwt();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setRole(role);

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User,null,customOAuth2User.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}

