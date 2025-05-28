package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitDateException;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.ScheduleConflictException;
import com.powerup.realestate.properties.domain.exceptions.UnauthorizedSellerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class VisitScheduleControllerAdvisor {

    @ExceptionHandler(InvalidVisitDateException.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handleInvalidVisitDateException(InvalidVisitDateException exception) {
        return ResponseEntity
                .badRequest()
                .body(new VisitScheduleExceptionResponse(
                        VisitScheduleExceptionConstants.INVALID_VISIT_DATE_EXCEPTION,
                        LocalDateTime.now()));
    }
    @ExceptionHandler (ScheduleConflictException.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handleInvalidSchedule(ScheduleConflictException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VisitScheduleExceptionResponse(
                        VisitScheduleExceptionConstants.SCHEDULE_EXIST,
                        LocalDateTime.now()));
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        String message = exception.getMessage();
        

        if (message != null && message.contains("fecha de fin")) {
            return ResponseEntity
                    .badRequest()
                    .body(new VisitScheduleExceptionResponse(
                            VisitScheduleExceptionConstants.INVALID_END_DATE_EXCEPTION,
                            LocalDateTime.now()));
        } else if (message != null && message.contains("horario")) {
            return ResponseEntity
                    .badRequest()
                    .body(new VisitScheduleExceptionResponse(
                            VisitScheduleExceptionConstants.INVALID_SCHEDULE_TIME_EXCEPTION,
                            LocalDateTime.now()));
        }

        return ResponseEntity
                .badRequest()
                .body(new VisitScheduleExceptionResponse(
                        message != null ? message : "Error en los datos de la solicitud",
                        LocalDateTime.now()));
    }
    
    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handlePropertyNotFoundException(PropertyNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new VisitScheduleExceptionResponse(
                        PropertyExceptionConstants.PROPERTY_NOT_FOUND_EXCEPTION,
                        LocalDateTime.now()));
    }
    
    @ExceptionHandler(UnauthorizedSellerException.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handleUnauthorizedSellerException(UnauthorizedSellerException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new VisitScheduleExceptionResponse(
                        VisitScheduleExceptionConstants.UNAUTHORIZED_SCHEDULE_ACCESS_EXCEPTION,
                        LocalDateTime.now()));
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<VisitScheduleExceptionResponse> handleGeneralException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new VisitScheduleExceptionResponse(
                        "Error interno del servidor: " + exception.getMessage(),
                        LocalDateTime.now()));
    }
} 