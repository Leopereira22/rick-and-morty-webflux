package com.pastorin.webrickandmortyapi.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handler(Exception e){
        ProblemDetail errors = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
