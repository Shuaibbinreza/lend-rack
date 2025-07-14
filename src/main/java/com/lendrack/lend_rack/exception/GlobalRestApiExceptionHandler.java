package com.lendrack.lend_rack.exception;

import com.lendrack.lend_rack.exception.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalRestApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleNotFoundException(NotFoundException e, WebRequest request) {
        log.error("Resource Not Found {}", e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setDetail("Resource Not Found");
        problemDetail.setStatus(HttpStatus.NOT_FOUND);
        return problemDetail;
    }
}
