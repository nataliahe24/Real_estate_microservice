package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public class BuyerVisitExceptionConstants {
    private BuyerVisitExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }
    
    public static final String MAX_VISITORS_EXCEEDED_EXCEPTION = "Este horario ya tiene el máximo de 2 compradores agendados";
    public static final String VISIT_SCHEDULE_NOT_FOUND_EXCEPTION = "El horario de visita no existe";
    public static final String PAST_SCHEDULE_ERROR_EXCEPTION = "No se puede agendar una visita en un horario que ya pasó";
    public static final String INVALID_BUYER_EMAIL_EXCEPTION = "El formato del email del comprador no es válido";


    public static final String BUYER_EMAIL_NULL_EXCEPTION = "El email del comprador no puede ser nulo o vacío";
    public static final String SCHEDULE_ID_NULL_EXCEPTION = "El ID del horario de visita no puede ser nulo";
} 