package com.powerup.realestate.properties.domain.utils.constants;

public final class LocationDomainConstants {
    private LocationDomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_CITY_NULL_MESSAGE = "El campo 'Ciudad' es obligatorio";
    public static final String FIELD_NEIGHBORHOOD_NULL_MESSAGE = "El campo 'Barrio' es obligatorio";
    public static final String FIELD_LOCATION_ALREADY_EXIST_MESSAGE = "Ubicación ya existe";
}
