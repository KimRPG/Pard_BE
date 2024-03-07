package com.pard.pard_backend.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pard.pard_backend.domain.project.entity.Project;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    private String part;

    @Column(length = 10)
    private String name;

    private Role role;

    @Column(length = 100)
    private String email;

    private String phoneNumber;

    private String generation;

    private String fcmToken;

    private boolean isAlarm;

    private float totalMinus;

    private float totalBonus;

    private String level;



    @JsonIgnore
    @JoinColumn(name = "PROJECT_ID")
    @ManyToOne (fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Project project;

    public static User toEntity(final @NotNull UserRequestDTO.Create request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .level(request.getLevel())
                .part(request.getPart())
                .build();
    }

}
