package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.infrastructure.entities.CityEntity;

public interface CityPersistencePort {
    CityEntity findByNameIgnoreCaseAndTrim(String cleanedName);
}
