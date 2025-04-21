package com.powerup.realestate.properties.infrastructure.exceptionshandler;


import com.powerup.realestate.properties.domain.exceptions.CityNonExistentException;
import com.powerup.realestate.properties.domain.exceptions.LocationAlreadyExist;
import com.powerup.realestate.properties.domain.exceptions.NeighborhoodNonNullException;
import com.powerup.realestate.properties.domain.utils.constants.LocationDomainConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class LocationControllerAdvisor {
    @ExceptionHandler(CityNonExistentException.class)
    public ResponseEntity<LocationExceptionResponse> handleCityNonExistentException(CityNonExistentException exception) {
        return ResponseEntity.badRequest().body(new LocationExceptionResponse(
                LocationExceptionConstants.CITY_NON_EXISTS_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(NeighborhoodNonNullException.class)
    public ResponseEntity<LocationExceptionResponse> handleNeighborhoodNonNullException(NeighborhoodNonNullException exception) {
        return ResponseEntity.badRequest().body(new LocationExceptionResponse(
                LocationDomainConstants.FIELD_NEIGHBORHOOD_NULL_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(LocationAlreadyExist.class)
    public ResponseEntity<LocationExceptionResponse> handleLocationAlreadyExist(LocationAlreadyExist exception) {
        return ResponseEntity.badRequest().body(
                new LocationExceptionResponse(LocationDomainConstants.FIELD_LOCATION_ALREADY_EXIST_MESSAGE, LocalDateTime.now())
        );
    }
}


