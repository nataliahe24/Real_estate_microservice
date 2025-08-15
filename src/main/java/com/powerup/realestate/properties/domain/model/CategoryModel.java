package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.utils.constants.CategoryDomainConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;


@Getter
@Builder
public class CategoryModel {
    @Setter
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, CategoryDomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description,  CategoryDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}
