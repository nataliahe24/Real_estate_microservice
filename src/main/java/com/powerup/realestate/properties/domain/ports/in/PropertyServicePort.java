package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.PropertyModel;



public interface PropertyServicePort {
    void save(PropertyModel propertyModel);
}

