package com.powerup.realestate.properties.application.services;

import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.PropertyModel;

public interface PropertyValidationService {

    void validate(PropertyModel property)
            throws  LocationNotFoundException, CategoryNotFoundException;
}
