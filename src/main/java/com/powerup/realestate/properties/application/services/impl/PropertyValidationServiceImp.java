package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.application.services.PropertyValidationService;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PropertyValidationServiceImp implements PropertyValidationService {
    private final LocationPersistencePort locationPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    public PropertyValidationServiceImp(LocationPersistencePort locationPersistencePort, CategoryPersistencePort categoryPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void validate(PropertyModel property) throws  LocationNotFoundException, CategoryNotFoundException {

        if (property.getLocation() == null) {
            throw new IllegalArgumentException(PropertyDomainContants.FIELD_LOCATION_NULL_MESSAGE);
        }
        if (property.getCategory() == null) {
            throw new IllegalArgumentException(PropertyDomainContants.FIELD_CATEGORY_NULL_MESSAGE);
        }

        Optional<LocationModel> locationModelOptional = locationPersistencePort.findByLocationId(property.getLocation().getId());
        if (locationModelOptional.isEmpty()) {
            throw new LocationNotFoundException();
        }

        Optional<CategoryModel> categoryModelOptional = categoryPersistencePort.getCategoryById(property.getCategory().getId());
        if (categoryModelOptional.isEmpty()) {
            throw new CategoryNotFoundException();
        }

    }
}

