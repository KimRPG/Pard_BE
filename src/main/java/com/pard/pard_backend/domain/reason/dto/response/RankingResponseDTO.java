package com.pard.pard_backend.domain.reason.dto.response;

import com.pard.pard_backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RankingResponseDTO {
    private String name;
    private String part;
    private Integer totalBonus;

    public static RankingResponseDTO toDTO(User user) {
        return RankingResponseDTO.builder()
                .name(user.getName())
                .part(user.getPart())
                .totalBonus(user.getTotalBonus())
                .build();
    }

}
