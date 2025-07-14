package com.seerbit.interview_test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppException extends RuntimeException {

    protected String message;
    protected int statusCode;

    public AppException(String message) {
        super(message);
        this.message = message;
    }
    public AppException(Exception e) {
        super(e);
    }
}
