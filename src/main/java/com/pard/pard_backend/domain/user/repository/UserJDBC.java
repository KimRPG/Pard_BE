package com.pard.pard_backend.domain.user.repository;

import com.pard.pard_backend.domain.security.jwt.JWTUtil;
import com.pard.pard_backend.domain.user.dto.request.UserTESTDTO;
import com.pard.pard_backend.domain.user.entity.User;
import com.pard.pard_backend.global.responses.errors.code.ProjectErrorCode;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJDBC {
    private final JdbcTemplate jdbcTemplate;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public UserTESTDTO check(String token) {
        String myEmail = jwtUtil.getEmail(token);
        User user = userRepository.findByEmail(myEmail).orElseThrow(()->new ProjectException.UserNotFound(ProjectErrorCode.USER_NOT_FOUND));
        String part = user.getPart();
        String generation = user.getGeneration();
        Integer totalBonus = (int)user.getTotalBonus();
        float totalMinus = user.getTotalBonus();
        UserTESTDTO userTESTDTO = new UserTESTDTO();

        // 첫 번째 쿼리: PARTITION BY를 사용한 랭킹
        jdbcTemplate.query(
                "SELECT email, RANK() OVER (PARTITION BY part ORDER BY total_bonus DESC) AS ranking FROM user WHERE generation = ? AND part = ?;",
                new Object[]{generation,part},
                (rs, rowNum) -> {
                    String email = rs.getString("email");
                    if (myEmail.equals(email)) {
                        userTESTDTO.setPartRanking(rs.getInt("ranking"));
                    }
                    return userTESTDTO;
                }
        );

        // 두 번째 쿼리: 전체 랭킹
        jdbcTemplate.query(
                "SELECT email, RANK() OVER (PARTITION BY generation ORDER BY total_bonus DESC) AS ranking FROM user WHERE generation=?;",
                new Object[]{generation},
                (rs, rowNum) -> {
                    String email = rs.getString("email");
                    if (myEmail.equals(email)) {
                        userTESTDTO.setTotalRanking(rs.getInt("ranking"));
                    }
                    return userTESTDTO;
                }
        );
        userTESTDTO.setEmail(myEmail);
        userTESTDTO.setTotalBonus(totalBonus);
        userTESTDTO.setTotalMinus(totalMinus);
        return userTESTDTO;
    }

    @Transactional
    public void deleteUserAttendance(final Long userId) {
        jdbcTemplate.update(
                "DELETE FROM attendance WHERE user_id = ?",
                userId
        );
    }

    @Transactional
    public void deleteUserReason(final Long userId) {
        jdbcTemplate.update(
                "DELETE FROM reason WHERE email = ?",
                userId
        );
    }

}
