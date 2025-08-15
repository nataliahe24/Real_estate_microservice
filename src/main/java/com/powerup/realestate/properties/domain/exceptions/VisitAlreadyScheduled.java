package com.powerup.realestate.properties.domain.exceptions;

public class VisitAlreadyScheduled extends RuntimeException {
    public VisitAlreadyScheduled(String message) {
        super(message);
    }
}
