package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.location.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.CityMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.DepartmentMaxSizeExceededException;
import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import lombok.Getter;

import java.util.Objects;

import static com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants.*;

public class LocationModel {

    private @Getter Long id;
    private @Getter String city;
    private @Getter String descriptionCity;
    private @Getter String department;
    private @Getter String descriptionDepartment;

    public LocationModel(Long id, String city, String descriptionCity, String department, String descriptionDepartment) {
        if (city.length() > CITY_MAX_SIZE) {
            throw new CityMaxSizeExceededException();
        }
        if (descriptionCity.length() > DESCRIPTION_MAX_SIZE ) {
            throw new DescriptionMaxSizeExceededException();
        }
        if (department.length() > DEPARTMENT_MAX_SIZE) {
            throw new DepartmentMaxSizeExceededException();
        }
        if (descriptionDepartment.length() > DESCRIPTION_MAX_SIZE ) {
            throw new DescriptionMaxSizeExceededException();
       }


        this.id = id;
        this.city = city;
        this.descriptionCity = descriptionCity;
        this.department = department;
        this.descriptionDepartment = descriptionDepartment;
    }

    public void setDepartment(String department) {
        this.department = Objects.requireNonNull(department,  LocationDomainConstants.FIELD_DEPARTMENT_NULL_MESSAGE);
    }

    public void setDescriptionDepartment(String descriptionDepartment) {
        this.descriptionDepartment = Objects.requireNonNull(descriptionDepartment,  LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
   }
    public void setCity(String city) {
        this.city = Objects.requireNonNull(city,  LocationDomainConstants.FIELD_CITY_NULL_MESSAGE);
    }
    public void setDescriptionCity(String descriptionCity) {
        this.descriptionCity = Objects.requireNonNull(descriptionCity,  LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    }
