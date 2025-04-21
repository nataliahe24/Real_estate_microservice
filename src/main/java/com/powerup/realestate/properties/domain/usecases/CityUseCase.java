package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.CityNonExistentException;
import com.powerup.realestate.properties.domain.ports.in.CityServicePort;
import com.powerup.realestate.properties.domain.ports.out.CityPersistencePort;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class CityUseCase implements CityServicePort {
    private final CityPersistencePort cityPersistencePort;

    @Autowired
    public CityUseCase(CityPersistencePort cityPersistencePort) {
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public CityEntity findCityByNameIgnoreCaseAndTrim(String cityName) {
        if (cityName == null || cityName.isEmpty()) {
            return null;
        }
        CityEntity cityEntity = cityPersistencePort.findByNameIgnoreCaseAndTrim(cityName);
        if (cityEntity == null) {
            throw new CityNonExistentException();
        }
        return cityEntity;
    }

}

