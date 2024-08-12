package com.pard.pard_backend.domain.ranking.dto.response;

import com.pard.pard_backend.domain.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponseDTO {
    private Integer ranking;
    private String name;
    private String part;
    private Integer totalBonus;

    public static RankingResponseDTO toDTO(User user) {
        return RankingResponseDTO.builder()
                .name(user.getName())
                .part(user.getPart())
                .totalBonus((int)user.getTotalBonus())
                .build();
    }

}
