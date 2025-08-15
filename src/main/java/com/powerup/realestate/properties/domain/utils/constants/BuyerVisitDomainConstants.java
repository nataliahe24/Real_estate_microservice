package com.powerup.realestate.properties.domain.utils.constants;

import java.util.regex.Pattern;

public class BuyerVisitDomainConstants {
    public static final String FIELD_VISIT_SCHEDULE_ID_NULL_MESSAGE = "El ID del horario de visita no puede ser nulo";
    public static final String FIELD_BUYER_EMAIL_NULL_MESSAGE = "El email del comprador no puede ser nulo";
    public static final String MAX_VISITORS_EXCEEDED = "Este horario ya tiene el máximo de 2 compradores agendados";
    public static final String BUYER_ALREADY_SCHEDULED = "Este comprador ya tiene una visita agendada para este horario";
    public static final String SCHEDULE_NOT_FOUND = "El horario de visita no existe";
    public static final String PAST_SCHEDULE_ERROR = "No se puede agendar una visita en un horario que ya pasó";
    public static final String INVALID_EMAIL_FORMAT_MESSAGE = "El formato del correo electrónico no es válido";
    

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
} 