package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.PropertyModel;


public interface PropertyPersistencePort {
    void save(PropertyModel propertyModel);
}

