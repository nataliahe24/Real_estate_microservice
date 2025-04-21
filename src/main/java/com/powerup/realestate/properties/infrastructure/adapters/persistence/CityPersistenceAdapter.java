package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.domain.ports.out.CityPersistencePort;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.CityRepository;

public class CityPersistenceAdapter implements CityPersistencePort {
    private final CityRepository cityRepository;

    public CityPersistenceAdapter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityEntity findByNameIgnoreCaseAndTrim(String cleanedName) {
        return cityRepository.findByNameIgnoreCaseAndTrim(cleanedName);
    }
}
