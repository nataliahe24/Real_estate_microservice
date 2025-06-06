package com.powerup.realestate.properties.domain.exceptions;

public class InvalidEndDateException extends RuntimeException {
    public InvalidEndDateException(String message) {
        super(message);
    }
}
