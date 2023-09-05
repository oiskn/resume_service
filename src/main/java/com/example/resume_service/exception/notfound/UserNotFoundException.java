package com.example.resume_service.exception.notfound;

import com.example.resume_service.exception.BaseNotFoundException;
import com.example.resume_service.exception.util.ErrorCode;

public class UserNotFoundException extends BaseNotFoundException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
