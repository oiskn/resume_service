package com.example.resume_service.exception;

import com.example.resume_service.exception.util.ErrorCode;

public class BaseNotFoundException extends BaseRuntimeException {
    public BaseNotFoundException(ErrorCode code) {
        super(code);
    }

    public BaseNotFoundException(ErrorCode code, String description) {
        super(code, description);
    }

    public BaseNotFoundException(ErrorCode code, String description, Throwable cause) {
        super(code, description, cause);
    }
}
