package com.powerup.realestate.category.domain.utils.constants;

public final class CategoryDomainConstants {
    private CategoryDomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' can not be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' can not be null";
    public static final int NAME_MAX_CHARACTERS = 50;
    public static final int DESCRPTION_MAX_CHARACTERS = 90;
}
