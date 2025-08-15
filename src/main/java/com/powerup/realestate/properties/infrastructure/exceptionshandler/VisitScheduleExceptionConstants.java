package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public class VisitScheduleExceptionConstants {

    private VisitScheduleExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }
    
    public static final String INVALID_VISIT_DATE_EXCEPTION = "Solo se pueden registrar horarios dentro de los próximos 3 semanas.";
    public static final String INVALID_END_DATE_EXCEPTION = "La fecha de fin debe ser posterior a la fecha de inicio";
    public static final String SCHEDULE_ALREADY_FULL_EXCEPTION = "Esta agenda de visita ya está completa";
    public static final String PAST_VISIT_DATE_EXCEPTION = "No se pueden programar visitas para fechas pasadas";
    public static final String UNAUTHORIZED_SCHEDULE_ACCESS_EXCEPTION = "No tienes permiso para acceder a esta agenda de visita";
    public static final String INVALID_SCHEDULE_TIME_EXCEPTION = "El horario de visita debe ser entre las 8:00 AM y 6:00 PM";
    public static final String SCHEDULE_EXIST = "El horario ya se encuentra disponible";
    public static final String GENERIC_INTERNAL_SERVER_ERROR = "Ha ocurrido un error inesperado. Por favor, contacte al soporte.";
    public static final String GENERIC_BAD_REQUEST = "Datos de solicitud incorrectos.";
} 