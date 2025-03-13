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
    public static final int CityMaxSize = 50;
    public static final int DepartmentMaxSize = 50;
    public static final int DescriptionMaxSize = 120;


}
