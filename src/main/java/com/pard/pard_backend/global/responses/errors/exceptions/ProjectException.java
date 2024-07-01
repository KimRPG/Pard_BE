package com.pard.pard_backend.global.responses.errors.exceptions;

import com.pard.pard_backend.global.responses.errors.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {

    private final ErrorCode errorCode;

    public static class NotFound extends ProjectException {
        public NotFound(ErrorCode errorCode) {
            super(errorCode);
        }
    }
    public static class UserNotFound extends ProjectException {
        public UserNotFound(ErrorCode errorCode) {
            super(errorCode);
        }
    }
    public static class WrongQR extends ProjectException {
        public WrongQR(ErrorCode errorCode) {
            super(errorCode);
        }
    }

    public static class NoUserQRTime extends ProjectException {
        public NoUserQRTime(ErrorCode errorCode) {
            super(errorCode);
        }
    }
    public static class ScheduleNotFound extends ProjectException {
        public ScheduleNotFound(ErrorCode errorCode) {
            super(errorCode);
        }
    }
    public static class ReasonNotFound extends ProjectException {
        public ReasonNotFound(ErrorCode errorCode) {
            super(errorCode);
        }
    }

    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}

