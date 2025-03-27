package com.powerup.realestate.category.domain.model;

import com.powerup.realestate.category.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.category.domain.exceptions.NameMaxSizeExceededException;
import com.powerup.realestate.category.domain.utils.constants.CategoryDomainConstants;
import lombok.Setter;

import java.util.Objects;

import static com.powerup.realestate.category.domain.utils.constants.CategoryDomainConstants.DESCRPTION_MAX_CHARACTERS;
import static com.powerup.realestate.category.domain.utils.constants.CategoryDomainConstants.NAME_MAX_CHARACTERS;

public class CategoryModel {
    @Setter
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        if (name.length() > NAME_MAX_CHARACTERS) throw new NameMaxSizeExceededException();
        if (description.length() > DESCRPTION_MAX_CHARACTERS) throw new DescriptionMaxSizeExceededException();
        this.id = id;
        this.name = Objects.requireNonNull(name, CategoryDomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description,  CategoryDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > NAME_MAX_CHARACTERS) throw new NameMaxSizeExceededException();
        this.name = Objects.requireNonNull(name, CategoryDomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > DESCRPTION_MAX_CHARACTERS) throw new DescriptionMaxSizeExceededException();
        this.description = Objects.requireNonNull(description,  CategoryDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}
