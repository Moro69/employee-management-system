package com.moro.web.rest.handler;

import com.moro.model.exception.ApiError;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.model.exception.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public <T extends Throwable> ResponseEntity<ApiError>
    handleNotFoundException(final T ex, final ServletWebRequest request) {
        log(ex.getLocalizedMessage());

        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getLocalizedMessage(),
                        request.getRequest().getRequestURI());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({StorageException.class})
    public <T extends Throwable> ResponseEntity<ApiError>
    handleStorageException(final T ex, final ServletWebRequest request) {
        log(ex.getLocalizedMessage());

        ApiError apiError =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        ex.getLocalizedMessage(),
                        request.getRequest().getRequestURI());

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public <T extends Throwable> ResponseEntity<ApiError>
    handleDataIntegrityViolationException(final T ex, final ServletWebRequest request) {
        log(ex.getLocalizedMessage());

        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getCause().getCause().getLocalizedMessage(),
                        request.getRequest().getRequestURI());

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    private void log(final String message) {
        log.info("Caught new api exception: {}", message);
    }
}
