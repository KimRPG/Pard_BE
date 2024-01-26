package com.pard.pard_backend.global.responses.errors.handler;

import com.pard.pard_backend.global.responses.errors.ErrorResponse;
import com.pard.pard_backend.global.responses.errors.exceptions.ProjectException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler {
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final @NotNull ProjectException e,
                                                                 final @NotNull HttpServletRequest request) {

        log.error("Handle ['business exception'] , message: '{}'" , e.getErrorCode().getMessage());

        return ErrorResponse.toResponseEntity(e.getErrorCode(), request.getRequestURI());
    }
}
