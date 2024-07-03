package com.pard.pard_backend.domain.user.controller;

import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.service.UserFacade;
import com.pard.pard_backend.domain.user.service.UserService;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody List<UserRequestDTO.Create> request) {
        userService.Create(request);
        return ResponseEntity.ok().body("유저들 추가 완료");
    }

    @GetMapping("")
    public List<UserResponseDTO.UserInfo>readAll(){
        return userService.findAll();
    }
    @GetMapping("/me")
    public UserResponseDTO.UserInfo readOne(@CookieValue(value = "Authorization") String token){
        return userFacade.findByToken(token);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO.Login request, HttpServletResponse response) throws ProjectException.UserNotFoundException {
        return ResponseEntity.ok().body(userFacade.login(request, response));
    }


}
