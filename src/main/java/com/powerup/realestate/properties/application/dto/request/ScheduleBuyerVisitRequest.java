package com.powerup.realestate.properties.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ScheduleBuyerVisitRequest(
    @NotNull(message = "El ID del horario de visita no puede ser nulo")
    Long scheduleId,
    
    @NotNull(message = "El email del comprador no puede ser nulo")
    @Email(message = "El formato del email no es válido")
    String buyerEmail
) {} 