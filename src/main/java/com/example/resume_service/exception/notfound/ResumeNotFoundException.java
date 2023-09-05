package com.example.resume_service.exception.notfound;

import com.example.resume_service.exception.BaseNotFoundException;
import com.example.resume_service.exception.util.ErrorCode;

public class ResumeNotFoundException extends BaseNotFoundException {

    public ResumeNotFoundException() {
        super(ErrorCode.RESUME_NOT_FOUND);
    }

}
