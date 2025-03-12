package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.category.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.category.domain.utils.constants.DomainConstants;
import com.powerup.realestate.location.domain.exceptions.CityMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.DepartmentMaxSizeExceededException;

import java.util.Objects;

public class LocationModel {
    private Long id;
    private String city;
    private String department;
    private String description;

    public LocationModel(Long id, String city, String department, String description) {
        if (city.length() > 50) {
            throw new CityMaxSizeExceededException();
        }
        if (department.length() > 120) {
            throw new DepartmentMaxSizeExceededException();
        }
        this.id = id;
        this.city = city;
        this.department = department;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        if (description.length() > 90) throw new DescriptionMaxSizeExceededException();
        this.description = Objects.requireNonNull(description,  DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
   }
    public void setDepartment(String department) {
        if (department.length() > 120) throw new DepartmentMaxSizeExceededException();
        this.department = Objects.requireNonNull(department,  DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public void setCity(String city) {
        if (city.length() > 50) throw new CityMaxSizeExceededException();
        this.city = Objects.requireNonNull(city,  DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }
}
