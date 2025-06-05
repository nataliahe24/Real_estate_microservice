package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import com.powerup.realestate.properties.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
;
import static com.powerup.realestate.properties.infrastructure.exceptionshandler.CategoryExceptionConstants.*;

@RestControllerAdvice

public class CategoryControllerAdvisor {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(CATEGORY_ALREADY_EXISTS,LocalDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NameMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameMaxSize(NameMaxSizeExceededException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(NAME_MAX_SIZE_EXCEEDED, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DescriptionMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionMaxSize(DescriptionMaxSizeExceededException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(DESCRIPTION_MAX_SIZE_EXCEEDED, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(CategoryExceptionConstants.CATEGORY_NON_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLocationNotFoundException(LocationNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(CategoryExceptionConstants.LOCATION_NON_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }
}
