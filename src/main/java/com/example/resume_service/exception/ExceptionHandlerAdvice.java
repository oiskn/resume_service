package com.example.resume_service.exception;

import com.example.resume_service.exception.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({BaseNotFoundException.class})
    public Response handleNotFoundException(BaseNotFoundException ex) {
        log.error("Common error", ex);
        return getErrorResponse(ex);
    }

    private Response getErrorResponse(BaseRuntimeException ex) {
        return Response.builder()
                .errCode((ex.getCode()))
                .errDescription(ex.getDescription())
                .build();
    }
}
