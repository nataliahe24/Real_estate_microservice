package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.category.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.CityMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.DepartmentMaxSizeExceededException;
import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import lombok.Getter;

import java.util.Objects;

import static com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants.*;

public class LocationModel {

    private @Getter Long id;
    private @Getter String city;
    private @Getter String department;
    private @Getter String description;

    public LocationModel(Long id, String city, String department, String description) {
        if (city.length() > CityMaxSize) {
            throw new CityMaxSizeExceededException();
        }
        if (department.length() > DepartmentMaxSize) {
            throw new DepartmentMaxSizeExceededException();
        }
        if (description.length() > DescriptionMaxSize ) {
            throw new DescriptionMaxSizeExceededException();
       }


        this.id = id;
        this.city = city;
        this.department = department;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description,  LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
   }
    public void setDepartment(String department) {
        this.department = Objects.requireNonNull(department,  LocationDomainConstants.FIELD_DEPARTMENT_NULL_MESSAGE);
    }

    public void setCity(String city) {
        this.city = Objects.requireNonNull(city,  LocationDomainConstants.FIELD_CITY_NULL_MESSAGE);
        }

    }
