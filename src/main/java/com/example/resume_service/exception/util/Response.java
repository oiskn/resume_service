package com.example.resume_service.exception.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {

    private final Long errCode;

    private final String errDescription;
}
