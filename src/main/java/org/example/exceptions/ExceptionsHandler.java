package org.example.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationExceptions(ConstraintViolationException e) {

        Map<String, String> message = new LinkedHashMap<>();
        for (ConstraintViolation<?> o : e.getConstraintViolations()) {
            message.put(o.getPropertyPath().toString(), e.getMessage());
        }

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionsMessageWrapper> handleMessageNotReadable(HttpMessageNotReadableException e) {

        ExceptionsMessageWrapper message = new ExceptionsMessageWrapper("JSON syntax error " + e.getCause().getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionsMessageWrapper> handleInternalExceptions(RuntimeException e) {

        LOGGER.error("Internal Server Error", e);
        ExceptionsMessageWrapper message = new ExceptionsMessageWrapper("Internal Server Error " + e.getCause().getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionsMessageWrapper> handleBadRequest(ResponseStatusException ex) {
        ExceptionsMessageWrapper message = new ExceptionsMessageWrapper(ex.getReason());

        return new ResponseEntity<>(message, ex.getStatus());
    }
}