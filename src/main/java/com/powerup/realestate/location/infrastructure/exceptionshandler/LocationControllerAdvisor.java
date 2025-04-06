package com.powerup.realestate.location.infrastructure.exceptionshandler;



import com.powerup.realestate.location.domain.exceptions.CityNonExistentException;
import com.powerup.realestate.location.domain.exceptions.NeighborhoodNonNullException;
import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class LocationControllerAdvisor {
    @ExceptionHandler(CityNonExistentException.class)
    public ResponseEntity<ExceptionResponse> handleCityNonExistentException(CityNonExistentException exception){
        return  ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.CITY_NON_EXISTS_EXCEPTION, LocalDateTime.now()));
    }
    @ExceptionHandler(NeighborhoodNonNullException.class)
    public ResponseEntity<ExceptionResponse> handleNeighborhoodNonNullException(NeighborhoodNonNullException exception){
        return  ResponseEntity.badRequest().body(new ExceptionResponse(LocationDomainConstants.FIELD_NEIGHBORHOOD_NULL_MESSAGE, LocalDateTime.now()));
    }
}


