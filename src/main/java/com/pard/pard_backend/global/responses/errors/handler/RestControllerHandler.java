package com.pard.pard_backend.global.responses.errors.handler;

import com.pard.pard_backend.global.responses.errors.ErrorResponse;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler {
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final @NotNull ProjectException e,
                                                                 final @NotNull HttpServletRequest request) {

        log.error("Handle ['business exception'] , message: '{}'", e.getErrorCode().getMessage());

        return ErrorResponse.toResponseEntity(e.getErrorCode(), request.getRequestURI());
    }

    @ExceptionHandler(ProjectException.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(ProjectException.UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        String errorMessage = "A null pointer exception occurred. Please check your request and try again.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<String> illegalAccessError(IllegalAccessError   ex) {
        String errorMessage = "A null pointer exception occurred. Please check your request and try again.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        // 예외 메시지를 사용자에게 반환
        String errorMessage = "이미 출석 체크 된 세미나 입니다.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
