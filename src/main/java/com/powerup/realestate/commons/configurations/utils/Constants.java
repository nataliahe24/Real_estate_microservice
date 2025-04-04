package com.powerup.realestate.commons.configurations.utils;

public final class Constants {
    public static final String SAVE_LOCATION_RESPONSE_MESSAGE = "Ubicación creada con éxito." ;
    public static final String PAGEABLE_FIELD_CITY = "cityId.name";
    public static final String PAGEABLE_FIELD_DEPARTMENT = "cityId.departmentEntity.name";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SAVE_CATEGORY_RESPONSE_MESSAGE = "Categoría creada con éxito.";
    public static final String PAGEABLE_FIELD_NAME = "name";
}
