package com.moro.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private HttpStatus status;

    public ApiException(final HttpStatus status, final Throwable cause, final String message) {
        super(message);
        this.status = status;
    }

    public ApiException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }
}
