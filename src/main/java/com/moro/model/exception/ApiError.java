package com.moro.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApiError {
    private LocalDateTime timeStamp = LocalDateTime.now();

    private int status;

    private String error;

    private String message;

    private String path;

    public ApiError(final int status,
                    final String error,
                    final String message,
                    final String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
