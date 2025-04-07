package com.powerup.realestate.properties.domain.utils.constants;

public final class CategoryDomainConstants {
    private CategoryDomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "El campo 'name' no puede estar vacío.";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "El campo 'descripción' no puede estar vacío.";
    public static final int NAME_MAX_CHARACTERS = 50;
    public static final int DESCRIPTION_MAX_CHARACTERS = 90;
}
