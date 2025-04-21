package com.powerup.realestate.properties.domain.utils.validation;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;

import java.util.Optional;

public class PropertyValidation {

    private PropertyValidation() {
        throw new UnsupportedOperationException();
    }

    public static void validatePropertyLocationAndCategory(PropertyModel propertyModel,
                                                           LocationPersistencePort locationPersistencePort,
                                                           CategoryPersistencePort categoryPersistencePort
    ) throws LocationNotFoundException, CategoryNotFoundException {

        if (propertyModel.getLocation() == null) {
            throw new IllegalArgumentException(PropertyDomainContants.FIELD_LOCATION_NULL_MESSAGE);
        }

        if (propertyModel.getCategory() == null) {
            throw new IllegalArgumentException(PropertyDomainContants.FIELD_CATEGORY_NULL_MESSAGE);
        }

        Optional<LocationModel> locationModelOptional =
                locationPersistencePort.findByLocationId(propertyModel.getLocation().getId());

        if (locationModelOptional.isEmpty()) {
            throw new LocationNotFoundException();
        }

        Optional<CategoryModel> categoryModelOptional =
                categoryPersistencePort.getCategoryById(propertyModel.getCategory().getId());

        if (categoryModelOptional.isEmpty()) {
            throw new CategoryNotFoundException();
        }
    }

}