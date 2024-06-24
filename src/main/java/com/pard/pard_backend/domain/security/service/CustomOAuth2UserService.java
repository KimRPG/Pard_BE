package com.pard.pard_backend.domain.security.service;

import com.pard.pard_backend.domain.security.dto.CustomOAuth2User;
import com.pard.pard_backend.domain.security.dto.GoogleResponse;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.domain.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        GoogleResponse oAuth2Response = null;
        if (registrationId.equalsIgnoreCase("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String email = oAuth2Response.getEmail();
        User existUser = userRepository.findByEmail(email);
        User user = User.toEntity(oAuth2Response.getName(), email);
        //나중에 거절 넣어놓기
        if (existUser == null) {
            userRepository.save(user);

            UserRequestDTO.Jwt userDTO = new UserRequestDTO.Jwt();
            userDTO.setName(oAuth2Response.getName());
            userDTO.setEmail(oAuth2Response.getEmail());
            userDTO.setRole("ROLE_YB");
            user.setRole("ROLE_YB");

            return new CustomOAuth2User(userDTO);

        } else {

            UserRequestDTO.Jwt userDTO = new UserRequestDTO.Jwt();
            userDTO.setName(oAuth2Response.getName());
            userDTO.setEmail(oAuth2Response.getEmail());
            userDTO.setRole("ROLE_YB");
            user.setRole("ROLE_YB");
            return new CustomOAuth2User(userDTO);
        }
    }
}
