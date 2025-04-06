package com.powerup.realestate.location.infrastructure.adapters.persistence;

import com.powerup.realestate.location.domain.ports.out.CityPersistencePort;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import com.powerup.realestate.location.infrastructure.repositories.mysql.CityRepository;

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
