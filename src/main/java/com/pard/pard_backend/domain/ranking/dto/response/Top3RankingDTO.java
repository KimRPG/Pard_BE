package com.pard.pard_backend.domain.ranking.dto.response;

import com.pard.pard_backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Top3RankingDTO {
    private String name;
    private String part;

    public static Top3RankingDTO toDTO(User user) {
        return Top3RankingDTO.builder()
                .name(user.getName())
                .part(user.getPart())
                .build();
    }
}
