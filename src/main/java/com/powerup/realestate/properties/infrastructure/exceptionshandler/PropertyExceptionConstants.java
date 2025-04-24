package com.powerup.realestate.properties.infrastructure.exceptionshandler;

public class PropertyExceptionConstants {
    private PropertyExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }
    
    public static final String PROPERTY_NOT_FOUND_EXCEPTION = "La propiedad no existe";
    public static final String INVALID_ROOMS_EXCEPTION = "El número de habitaciones no puede ser negativo";
    public static final String INVALID_BATHROOMS_EXCEPTION = "El número de baños no puede ser negativo";
    public static final String INVALID_ACTIVE_PUBLICATION_DATE_EXCEPTION = "La fecha de publicación activa no puede ser mayor a un mes en el futuro";
    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "La categoría no existe";
    public static final String LOCATION_NOT_FOUND_EXCEPTION = "La ubicación no existe";
    public static final String UNAUTHORIZED_SELLER_EXCEPTION = "No tienes permiso para gestionar esta propiedad";
} 