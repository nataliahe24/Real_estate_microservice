package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.application.services.PropertyValidationService;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;


public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;
    private final PropertyValidationService propertyValidationService;

    public PropertyUseCase(PropertyPersistencePort propertyPersistencePort, PropertyValidationService propertyValidationService) {
        this.propertyPersistencePort = propertyPersistencePort;
        this.propertyValidationService = propertyValidationService;
    }
    @Override
    public void save(PropertyModel propertyModel) throws LocationNotFoundException, CategoryNotFoundException {
        propertyValidationService.validate(propertyModel);
        propertyPersistencePort.save(propertyModel);
    }

}
