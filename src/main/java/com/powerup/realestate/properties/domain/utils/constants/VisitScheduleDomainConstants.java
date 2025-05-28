package com.powerup.realestate.properties.domain.utils.constants;

public final class VisitScheduleDomainConstants {
    public static final String FIELD_SELLER_ID_NULL_MESSAGE = "El ID del vendedor no puede ser nulo.";
    public static final String FIELD_PROPERTY_NULL_MESSAGE = "La propiedad no puede ser nula.";
    public static final String FIELD_START_DATE_NULL_MESSAGE = "La fecha de inicio no puede ser nula.";
    public static final String FIELD_END_DATE_NULL_MESSAGE = "La fecha de fin no puede ser nula.";
    public static final String INVALID_FUTURE_DATE_MESSAGE = "La fecha de visita debe estar dentro de las próximas 3 semanas.";
    public static final String INVALID_END_DATE_MESSAGE = "La fecha de fin debe ser posterior a la fecha de inicio.";
    public static final String SAVE_VISIT_SCHEDULE_RESPONSE_MESSAGE = "Horario de visita creado exitosamente.";
    public static final String UNAUTHORIZED_SELLER_MESSAGE = "El vendedor no es propietario de esta propiedad.";
    public static final String PROPERTY_NOT_FOUND_MESSAGE = "Propiedad no encontrada";
    public static final String SCHEDULE_ALL_EXIST = "Ya existe una visita agendada en ese rango de fechas y horas.";
} 