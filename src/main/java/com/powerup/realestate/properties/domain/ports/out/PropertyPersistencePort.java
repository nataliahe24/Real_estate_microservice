package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;

import java.util.List;


public interface PropertyPersistencePort {
    void save(PropertyModel propertyModel);
    List<PropertyModel> findByPublicationStatus(PublicationStatus status);
    void update(PropertyModel property);
}

