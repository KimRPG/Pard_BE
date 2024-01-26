package com.pard.pard_backend.global.responses.errors.exceptions;

import com.pard.pard_backend.global.responses.errors.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {

    private final ErrorCode errorCode;

    public static class NotFound extends ProjectException {
        public NotFound(ErrorCode errorCode) {
            super(errorCode);
        }
    }

}
