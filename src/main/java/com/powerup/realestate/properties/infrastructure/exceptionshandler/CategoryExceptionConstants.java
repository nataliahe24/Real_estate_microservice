package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public class CategoryExceptionConstants {
    private CategoryExceptionConstants() {
    }

    public static final String CATEGORY_ALREADY_EXISTS = "La categoría ya existe.";
    public static final String NAME_MAX_SIZE_EXCEEDED = "El nombre de la categoría no puede exceder los 50 caracteres";
    public static final String DESCRIPTION_MAX_SIZE_EXCEEDED = "La descripción de la categoría no puede exceder los 90 caracteres";
    public static final String CATEGORY_NON_EXISTS_EXCEPTION = "Categoría no existe";
    public static final String LOCATION_NON_EXISTS_EXCEPTION = "Ubicación no existe";
}

