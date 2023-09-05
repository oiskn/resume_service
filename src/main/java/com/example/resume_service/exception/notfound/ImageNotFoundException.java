package com.example.resume_service.exception.notfound;

import com.example.resume_service.exception.BaseNotFoundException;
import com.example.resume_service.exception.util.ErrorCode;

public class ImageNotFoundException extends BaseNotFoundException {

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
