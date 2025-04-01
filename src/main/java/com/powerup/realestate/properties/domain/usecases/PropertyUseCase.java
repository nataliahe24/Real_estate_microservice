package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;



public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;

    public PropertyUseCase(PropertyPersistencePort propertyPersistencePort) {
        this.propertyPersistencePort = propertyPersistencePort;
    }
    @Override
    public void save(PropertyModel propertyModel) {
        propertyPersistencePort.save(propertyModel);
    }
}
