package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.infrastructure.entities.CityEntity;

public interface CityServicePort {
    CityEntity findCityByNameIgnoreCaseAndTrim(String cityName);
}
