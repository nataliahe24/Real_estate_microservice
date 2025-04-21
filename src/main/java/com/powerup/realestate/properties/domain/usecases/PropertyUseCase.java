package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.domain.utils.validation.PropertyValidation;


public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;
    private final LocationPersistencePort locationPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;


    public PropertyUseCase(PropertyPersistencePort propertyPersistencePort,
                           LocationPersistencePort locationPersistencePort,
                           CategoryPersistencePort categoryPersistencePort) {
        this.propertyPersistencePort = propertyPersistencePort;
        this.locationPersistencePort = locationPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void saveProperty(PropertyModel propertyModel) throws LocationNotFoundException, CategoryNotFoundException {
        PropertyValidation.validatePropertyLocationAndCategory(
                propertyModel,
                locationPersistencePort,
                categoryPersistencePort
        );
        propertyModel.setPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);

        propertyPersistencePort.save(propertyModel);
    }

    @Override
    public PageResult<PropertyModel> getProperties(Integer page, Integer size, String location,
                                                   String category, Integer rooms, Integer bathrooms,
                                                   Double minPrice, Double maxPrice, String sortBy, boolean orderAsc) {
        return propertyPersistencePort.getProperties(
                page,
                size,
                location,
                category,
                rooms,
                bathrooms,
                minPrice,
                maxPrice,
                sortBy,
                orderAsc);
    }

}
