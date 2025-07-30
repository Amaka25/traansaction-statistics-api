package com.seerbit.interview_test.exceptions;

import com.seerbit.interview_test.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException exception, WebRequest request) {
        int statusCode = exception.getStatusCode();

        // Handle special case: return 204 No Content
        if (statusCode == 204) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        }

        if (statusCode == 422) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build(); // HTTP 422 No Content
        }

        // Handle all other AppException scenarios
        ResponseDto res = new ResponseDto();
        res.setMessage(exception.getMessage());
        res.setStatusCode(statusCode == 0 ? HttpStatus.CONFLICT.value() : statusCode);
        res.setData(null);

        HttpStatus responseStatus = statusCode == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.valueOf(statusCode);
        return new ResponseEntity<>(res, responseStatus);
    }

}
