package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public final class ExceptionConstants {
    private ExceptionConstants(){}

    public static final String NAME_MAX_SIZE_MESSAGE = "El nombre de la categoría no puede exceder los 50 caracteres";
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "La descripción de la categoría no puede exceder los 90 caracteres";
    public static final String CATEGORY_EXISTS_EXCEPTION = "La categoría ya existe";
    public static final String CATEGORY_NON_EXISTS_EXCEPTION = "Categoría no existe";
    public static final String LOCATION_NON_EXISTS_EXCEPTION = "Ubicación no existe";
}
