package com.example.resume_service.exception;

import com.example.resume_service.exception.util.ErrorCode;
import lombok.Data;

@Data
public class BaseRuntimeException extends RuntimeException {
    private final Long code;
    private final String description;

    public BaseRuntimeException(ErrorCode code) {
        this(code, code.getMessage(), null);
    }

    public BaseRuntimeException(ErrorCode code, String description) {
        this(code, description, null);
    }

    public BaseRuntimeException(ErrorCode code, String description, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code.getCode();
        this.description = description;
    }
}