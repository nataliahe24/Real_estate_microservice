package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public class VisitScheduleExceptionConstants {
    private VisitScheduleExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }
    
    public static final String INVALID_VISIT_DATE_EXCEPTION = "La fecha de visita no puede ser más de tres semanas en el futuro";
    public static final String INVALID_END_DATE_EXCEPTION = "La fecha de fin debe ser posterior a la fecha de inicio";
    public static final String SCHEDULE_NOT_FOUND_EXCEPTION = "La agenda de visita no existe";
    public static final String SCHEDULE_ALREADY_FULL_EXCEPTION = "Esta agenda de visita ya está completa";
    public static final String PAST_VISIT_DATE_EXCEPTION = "No se pueden programar visitas para fechas pasadas";
    public static final String UNAUTHORIZED_SCHEDULE_ACCESS_EXCEPTION = "No tienes permiso para acceder a esta agenda de visita";
    public static final String INVALID_SCHEDULE_TIME_EXCEPTION = "El horario de visita debe ser entre las 8:00 AM y 6:00 PM";
} 