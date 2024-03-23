package com.pard.pard_backend.global.responses.errors.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProjectErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "게시판 정보를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
