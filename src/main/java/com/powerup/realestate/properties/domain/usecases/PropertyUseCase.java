package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;


public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final LocationPersistencePort locationPersistencePort;

    public PropertyUseCase(PropertyPersistencePort propertyPersistencePort, CategoryServicePort categoryServicePort, CategoryPersistencePort categoryPersistencePort, LocationServicePort locationServicePort, LocationPersistencePort locationPersistencePort) {
        this.propertyPersistencePort = propertyPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.locationPersistencePort = locationPersistencePort;
    }
    @Override
    public void save(PropertyModel propertyModel) {
        Long locationId = propertyModel.getLocationId();
        Long categoryId = propertyModel.getCategoryId();


        LocationModel locationModel = null;
        if (locationId != null) {
            locationModel = locationPersistencePort.findByLocationId(locationId);
            if (locationModel == null) {
                throw new LocationNotFoundException();
            }
        } else {
            throw new IllegalArgumentException();
        }

        CategoryModel categoryModel = null;
        if (categoryId != null) {
            categoryModel = categoryPersistencePort.getCategoryById(categoryId);
            if (categoryModel == null) {
                throw new CategoryNotFoundException();
            }
        } else {
            throw new IllegalArgumentException();
        }

        propertyPersistencePort.save(propertyModel);
    }

}
