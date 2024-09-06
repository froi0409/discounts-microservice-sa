package com.froi.discounts.common.infrastructure.restapi;

import com.froi.discounts.common.exceptions.DuplicatedEntityException;
import com.froi.discounts.common.exceptions.IllegalEnumException;
import com.froi.discounts.common.exceptions.NetworkMicroserviceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    public ResponseEntity<String> handleDuplicatedEntityException(DuplicatedEntityException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(NetworkMicroserviceException.class)
    public ResponseEntity<String> handleNetworkMicroserviceException(NetworkMicroserviceException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(IllegalEnumException.class)
    public ResponseEntity<String> handleIllegalEnumException(IllegalEnumException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
