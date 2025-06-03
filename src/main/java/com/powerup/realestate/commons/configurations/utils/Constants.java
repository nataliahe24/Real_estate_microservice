package com.powerup.realestate.commons.configurations.utils;

public final class Constants {
    public static final String SAVE_LOCATION_RESPONSE_MESSAGE = "Ubicación creada con éxito." ;
    public static final String PAGEABLE_FIELD_CITY = "cityName.name";
    public static final String PAGEABLE_FIELD_DEPARTMENT = "cityName.departmentEntity.name";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SAVE_CATEGORY_RESPONSE_MESSAGE = "Categoría creada con éxito.";
    public static final String PAGEABLE_FIELD_NAME = "name";
    public static final String SAVE_PROPERTY_RESPONSE_MESSAGE = "Publicación creada con éxito.";
    public static final String SAVE_VISIT_SCHEDULE_RESPONSE_MESSAGE = "visita programada con éxito";
    public static final String VISIT_SCHEDULE_NO_FOUND = "visita no encontrada";

}
