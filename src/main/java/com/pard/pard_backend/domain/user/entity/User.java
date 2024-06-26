package com.pard.pard_backend.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pard.pard_backend.domain.project.entity.Project;
import com.pard.pard_backend.domain.user.dto.request.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Setter
    private String part;

    @Column(length = 10)
    private String name;

    @Setter
    private String role;

    @Column(length = 100)
    private String email;

    private String phoneNumber;

    private String generation;

    private String fcmToken;

    private boolean isAlarm;

    @Setter
    @ColumnDefault("0")
    private float totalMinus;

    @Setter
    @ColumnDefault("0")
    private float totalBonus;

    @Setter
    @ColumnDefault("0")
    private float pangoolPoint;



    @JsonIgnore
    @JoinColumn(name = "PROJECT_ID")
    @ManyToOne (fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Project project;

    public static User toEntity(final @NotNull UserRequestDTO.Create request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .part(request.getPart())
                .build();
    }

    public static User toEntity(String name, String email) {

        return User.builder()
                .name(name)
                .email(email)
                .build();
    }


}
