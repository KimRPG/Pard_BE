package com.pard.pard_backend.user.controller;

import com.pard.pard_backend.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseDTO.Create> create(@RequestBody UserRequestDTO.Create request){
        return ResponseEntity.ok(userService.Create(request));

    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
