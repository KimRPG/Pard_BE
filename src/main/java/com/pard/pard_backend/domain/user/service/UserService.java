package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponseDTO.UserInfo findByEmail(String email) {
        return UserResponseDTO.UserInfo.toDto(userRepository.findByEmail(email).orElseThrow(()-> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND)));
    }
    public void Create(List<UserRequestDTO.Create> request){
        for (UserRequestDTO.Create userRequest : request) {
            if (!userRepository.existsByEmail(userRequest.getEmail())) {
                userRepository.save(User.toEntity(userRequest));
            }

        }

    }

    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    };

    public List<UserResponseDTO.UserInfo> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO.UserInfo::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO.UserInfo login(String email) throws ProjectException.UserNotFoundException {
        //if문 안에 넣기
        if (!userRepository.existsByEmail(email)) {
        throw new ProjectException.UserNotFoundException(String.format("%s 을(를) 못 찾았어요", email));
        }
        return UserResponseDTO.UserInfo.toDto(userRepository.findByEmail(email).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND)));
    }

}
