package com.pard.pard_backend.domain.user.service;

import com.pard.pard_backend.domain.cookie.service.CookieService;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.dto.response.UserResponseDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserJDBC;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserJDBC userJDBC;
    private final CookieService cookieService;


    public UserResponseDTO.UserInfo findByEmail(String email) {
        return UserResponseDTO.UserInfo.toDto(userRepository.findByEmail(email).orElseThrow(()-> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND)));
    }
    public void Create(List<UserRequestDTO.Create> request){
        for (UserRequestDTO.Create userRequest : request) {
            if (!userRepository.existsByEmail(userRequest.getEmail())) {
                userRepository.save(User.toEntity(userRequest));
            } else{
                User existingUser =  userRepository.findByEmail(userRequest.getEmail()).orElseThrow(()->new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
                User patchUser = User.patchUser(existingUser, userRequest);

                userRepository.save(patchUser);
            }

        }

    }


    public List<UserResponseDTO.UserInfoAdmin> findByGeneration(String generation) {
        return userRepository.findByGenerationOrderByNameDesc(generation)
                .stream()
                .map(UserResponseDTO.UserInfoAdmin::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO.UserInfo login(String email, String deviceToken, HttpServletResponse response) throws ProjectException.UserNotFoundException {
        if (deviceToken != null) {
            Optional<User> userOp = userRepository.findByEmail(email);
            if (!userOp.isPresent()) {
                cookieService.clearJwtCookie(response);
                throw new ProjectException.UserNotFoundException(String.format("%s 을(를) 못 찾았어요", email));
            } else {
                User user = userOp.get();
                user.updateFCMToken(deviceToken);
                userRepository.save(user);
            }
        }
        return UserResponseDTO.UserInfo.toDto(userRepository.findByEmail(email).orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND)));
    }

    @Transactional
    public void deleteUser(String email) {
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND))
                .getUserId();

        userJDBC.deleteUserAttendance(userId);
        userJDBC.deleteUserReason(userId);
        userRepository.deleteById(userId);
    }

    public List<User> happyBirthDayUser(String now){
        return userRepository.findByBirthDay(now);
    }


}
