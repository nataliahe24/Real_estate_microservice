package com.powerup.realestate.properties.domain.exceptions;

public class InvalidVisitDateException extends RuntimeException {
    public InvalidVisitDateException() {
        super("La fecha de visita debe estar dentro de las próximas 3 semanas");
    }
} 