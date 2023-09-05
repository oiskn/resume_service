package com.example.resume_service.exception.notfound;

import com.example.resume_service.exception.BaseNotFoundException;
import com.example.resume_service.exception.util.ErrorCode;

public class RoleNotFoundException extends BaseNotFoundException {

    public RoleNotFoundException() {
        super(ErrorCode.ROLE_NOT_FOUND);
    }
}
