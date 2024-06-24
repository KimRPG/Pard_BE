package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
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

//    public void updateAttendance(Long userId,)
}
