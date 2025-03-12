package com.powerup.realestate.location.infrastructure.exceptionshandler;

import com.powerup.realestate.location.domain.exceptions.DeparmentAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class LocationControllerAdvisor {
    @ExceptionHandler(DeparmentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleLocationAlreadyExistsException(DeparmentAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.DEPARTMENT_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }
}
