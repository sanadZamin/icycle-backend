package com.joprog.icycle.Exceptions;

import com.joprog.icycle.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity handleException(EmailValidationException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errorCode("400")
                        .errorMessage("Validation Error")
                        .errorDescription("Email Format is invalid")
                        .build());
    }
}
