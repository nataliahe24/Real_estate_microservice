package com.powerup.realestate.location.domain.ports.out;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;

public interface CityPersistencePort {
    CityEntity findByNameIgnoreCaseAndTrim(String cleanedName);
}
