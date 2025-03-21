package com.powerup.realestate.category.domain.model;

import com.powerup.realestate.category.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.category.domain.exceptions.NameMaxSizeExceededException;
import com.powerup.realestate.category.domain.utils.constants.CategoryDomainConstants;

import java.util.Objects;

public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        if (name.length() > 50) throw new NameMaxSizeExceededException();
        if (description.length() > 90) throw new DescriptionMaxSizeExceededException();
        this.id = id;
        this.name = Objects.requireNonNull(name, CategoryDomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description,  CategoryDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, CategoryDomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 90) throw new DescriptionMaxSizeExceededException();
        this.description = Objects.requireNonNull(description,  CategoryDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}
