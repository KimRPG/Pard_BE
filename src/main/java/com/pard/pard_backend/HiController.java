package com.pard.pard_backend;

import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HiController {
    private final JWTUtil jwtUtil;
    @GetMapping("/hi/hello")
    public String hi(@CookieValue(value = "Authorization") String authorization) {
        System.out.println(jwtUtil.getEmail(authorization));
        return "hi";
    }
    @GetMapping("/hi/hi")
    public String hihi() {
        return "hi";
    }
    @GetMapping("/test")
    public String hello(HttpServletResponse response) {
        String name = "hi";
        String role = "ROLE_YB";
        String email = "cjh";
        String token = jwtUtil.createJwt(name, role, email);

        Cookie cookie = new Cookie("Authorization", token); // Name-Value로 쿠키를 만듦
        cookie.setPath("/"); //
        cookie.setSecure(true); //https 사용한다면 켜주세요
        cookie.setAttribute("SameSite", "None"); //이거는 나중에 설명
        cookie.setMaxAge(30*60*60*60); //이거는 몇 초동안 쿠키를 유지할 것인지
        cookie.setHttpOnly(false); // JavaScript에서 쿠키에 접근할 수 없도록 함
        System.out.println("들어옴");

        response.addCookie(cookie);
        return "hello";
    }
}
