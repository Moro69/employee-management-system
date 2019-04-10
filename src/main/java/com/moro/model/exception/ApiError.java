package com.moro.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApiError {
    private LocalDateTime timeStamp = LocalDateTime.now();

    private HttpStatus status;

    private String message;

    private String path;

    public ApiError(final HttpStatus status,
                    final String message,
                    final String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
