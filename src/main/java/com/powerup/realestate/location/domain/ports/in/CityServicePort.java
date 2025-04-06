package com.powerup.realestate.location.domain.ports.in;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;

public interface CityServicePort {
    CityEntity findCityByNameIgnoreCaseAndTrim(String cityName);
}
