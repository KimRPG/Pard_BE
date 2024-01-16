package com.pard.pard_backend.user.service;

import com.pard.pard_backend.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.user.entity.User;
import com.pard.pard_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO.Create Create(UserRequestDTO.Create request){
        User user = userRepository.save(User.toEntity(request));
        return UserResponseDTO.Create.toDto(userRepository.save(user));

    }
}
