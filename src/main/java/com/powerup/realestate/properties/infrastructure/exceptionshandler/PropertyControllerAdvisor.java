package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.InvalidBathroomsException;
import com.powerup.realestate.properties.domain.exceptions.InvalidRoomsException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.UnauthorizedSellerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PropertyControllerAdvisor {

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyNotFoundException(PropertyNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.PROPERTY_NOT_FOUND_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidRoomsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRoomsException(InvalidRoomsException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.INVALID_ROOMS_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidBathroomsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidBathroomsException(InvalidBathroomsException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.INVALID_BATHROOMS_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidActivePublicationDateException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidActivePublicationDateException(
            InvalidActivePublicationDateException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.INVALID_ACTIVE_PUBLICATION_DATE_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.CATEGORY_NOT_FOUND_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLocationNotFoundException(LocationNotFoundException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.LOCATION_NOT_FOUND_EXCEPTION, 
                        LocalDateTime.now()));
    }

    @ExceptionHandler(UnauthorizedSellerException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorizedSellerException(UnauthorizedSellerException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ExceptionResponse(
                        PropertyExceptionConstants.UNAUTHORIZED_SELLER_EXCEPTION, 
                        LocalDateTime.now()));
    }
} 