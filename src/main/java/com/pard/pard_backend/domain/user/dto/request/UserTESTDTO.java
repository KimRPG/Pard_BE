package com.pard.pard_backend.domain.user.dto.request;

import lombok.Data;

@Data
public class UserTESTDTO {
    private String email;
    private Integer partRanking;
    private Integer totalRanking;
    private Integer totalBonus;
    private float totalMinus;
}
