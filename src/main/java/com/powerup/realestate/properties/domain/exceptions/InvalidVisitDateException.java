package com.powerup.realestate.properties.domain.exceptions;

import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.INVALID_FUTURE_DATE_MESSAGE;

public class InvalidVisitDateException extends RuntimeException {
    public InvalidVisitDateException() {
        super(INVALID_FUTURE_DATE_MESSAGE);
    }
} 