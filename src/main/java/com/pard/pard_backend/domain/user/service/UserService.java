package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
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
        return UserResponseDTO.UserInfo.toDto(userRepository.findByEmail(email));
    }
    public UserResponseDTO.Create Create(UserRequestDTO.Create request){

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            user = userRepository.save(User.toEntity(request));
        }
        else {
            user.setPart(request.getPart());
            userRepository.save(user);
        }

        return UserResponseDTO.Create.toDto(userRepository.save(user));

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



//    public void updateAttendance(Long userId,)
}
