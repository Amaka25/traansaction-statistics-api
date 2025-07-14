package com.seerbit.interview_test.exceptions;

import com.seerbit.interview_test.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        ResponseDto res = new ResponseDto();
        res.setMessage(exception.getMessage());
        res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setData(null);
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException exception, WebRequest request) {
        ResponseDto res = new ResponseDto();
        res.setMessage(exception.getMessage());
        res.setStatusCode(HttpStatus.CONFLICT.value());
        res.setData(null);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
