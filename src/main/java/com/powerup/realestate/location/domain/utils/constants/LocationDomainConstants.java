package com.powerup.realestate.location.domain.utils.constants;

public final class LocationDomainConstants {
    private LocationDomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    /*
    Validation messages
     */
    public static final String FIELD_CITY_NULL_MESSAGE = "Field 'city' can not be null";
    public static final String FIELD_DEPARTMENT_NULL_MESSAGE = "Field 'department' can not be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' can not be null";
    public static final int CITY_MAX_SIZE = 50;
    public static final int DEPARTMENT_MAX_SIZE = 50;
    public static final int DESCRIPTION_MAX_SIZE = 120;


}
