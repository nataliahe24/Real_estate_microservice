package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import com.powerup.realestate.properties.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BuyerVisitControllerAdvisor {

    @ExceptionHandler(ScheduleNotFountExceptions.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleScheduleNotFountExceptions(ScheduleNotFountExceptions exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.VISIT_SCHEDULE_NOT_FOUND_EXCEPTION,
                        LocalDateTime.now()));
    }
    @ExceptionHandler(VisitAlreadyScheduled.class)
    public ResponseEntity<BuyerVisitExceptionResponse> VisitAlreadyScheduledException (VisitAlreadyScheduled exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.BUYER_ALREADY_SCHEDULED,
                        LocalDateTime.now()));
    }

    @ExceptionHandler(MaxVisitException.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleMaxVisitException(MaxVisitException exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.MAX_VISITORS_EXCEEDED_EXCEPTION,
                        LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidVisitScheduleException.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleInvalidVisitScheduleException(InvalidVisitScheduleException exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.PAST_SCHEDULE_ERROR_EXCEPTION,
                        LocalDateTime.now()));
    }

    @ExceptionHandler(BuyerEmailNotNullException.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleBuyerEmailNotNullException(BuyerEmailNotNullException exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.BUYER_EMAIL_NULL_EXCEPTION,
                        LocalDateTime.now()));
    }

    @ExceptionHandler(ScheduleIdNotNullException.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleScheduleIdNotNullException(ScheduleIdNotNullException exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.SCHEDULE_ID_NULL_EXCEPTION,
                        LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<BuyerVisitExceptionResponse> handleInvalidEmailFormatException(InvalidEmailFormatException exception) {
        return ResponseEntity
                .badRequest()
                .body(new BuyerVisitExceptionResponse(
                        BuyerVisitExceptionConstants.INVALID_BUYER_EMAIL_EXCEPTION,
                        LocalDateTime.now()));
    }

} 