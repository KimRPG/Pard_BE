package com.pard.pard_backend.domain.user.controller;

import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import com.pard.pard_backend.domain.user.service.UserFacade;
import com.pard.pard_backend.domain.user.service.UserService;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    private final UserJDBC userJDBC;
    @PostMapping("")
    @Operation(summary = "사용자 정보를 List 형식으로 입력받고, 해당 유저들을 추가해줍니다.", description = "name, email, part, phoneNumber, role, generation을 입력받고 해당 유저 정보를 추가합니다.")
    public ResponseEntity<String> create(@RequestBody List<UserRequestDTO.Create> request) {
        userService.Create(request);
        return ResponseEntity.ok().body("유저들 추가 완료");
    }

    @GetMapping("/{generation}")
    @Operation(summary = "해당 기수의 모든 인원 정보를 출력합니다.", description = "기수의 번호를 입력하면, 해당 기수의 모든 인원의 정보를 반환해줍니다.")
    public List<UserResponseDTO.UserInfoAdmin>readAll(@PathVariable String generation){
        return userService.findByGeneration(generation);
    }

    @GetMapping("/me")
    @Operation(summary = "토큰으로 유저의 정보를 찾고 해당 인원의 정보를 출력해줍니다.", description = "토큰을 입력하면 해당 토큰의 유저 정보를 반환해줍니다.")
    public UserResponseDTO.UserInfo readOne(@CookieValue(value = "Authorization") String token){
        return userFacade.findByToken(token);
    }

    @DeleteMapping("")
    @Operation(summary = "유저 정보를 이메일 주소로 삭제합니다.", description = "유저의 이메일을 입력하면 해당 유저의 정보를 삭제합니다.")
    public ResponseEntity<?> delete(@RequestParam(required = false) String email,@CookieValue(value = "Authorization") String token, HttpServletResponse response){
        if (email == null) {
            userFacade.deleteByToken(token, response);
        }
        userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(summary = "이메일을 입력하고 토큰을 받아옵니다.", description = "유저의 이메일을 입력하면, 해당 유저의 servlet response에 토큰을 발급해줍니다.")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO.Login request, HttpServletResponse response) throws ProjectException.UserNotFoundException {
        return ResponseEntity.ok().body(userFacade.login(request, response));
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void schedule() {
        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
        userFacade.happyBirthDayUser(now);
    }



}
