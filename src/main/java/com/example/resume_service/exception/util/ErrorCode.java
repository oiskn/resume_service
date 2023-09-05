package com.example.resume_service.exception.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(1L, "User not found."),
    IMAGE_NOT_FOUND(2L, "Image not found."),
    RESUME_NOT_FOUND(3L, "Resume not found."),
    ROLE_NOT_FOUND(4L, "Role not found.");

    private final Long code;

    private final String message;
}
