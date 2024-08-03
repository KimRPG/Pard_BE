package com.pard.pard_backend;

import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HiController {
    private final JWTUtil jwtUtil;
    @GetMapping("/hi/hello")
    @Operation(summary = "쿠키 유효성 검사", description = "입력한 쿠키의 유효성을 확인합니다.")
    public String hi(@CookieValue(value = "Authorization") String authorization) {
        System.out.println(jwtUtil.getEmail(authorization));
        return "hi";
    }

    @GetMapping("/hi/hi")
    @Operation(summary = "테스트 코드", description = "구현된게 없어서 403 반환합니다.")
    public String hihi() {
        return "hi";
    }

    @GetMapping("/test")
    @Operation(summary = "JWT 생성, 쿠키 설정 후, hello 반환", description = "name, role, email 기반으로 JWT를 생성하고, 여러 쿠키 속성을 설정해줍니다.")
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
