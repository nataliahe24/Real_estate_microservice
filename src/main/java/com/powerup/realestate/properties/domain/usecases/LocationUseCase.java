package com.powerup.realestate.properties.domain.usecases;


import com.powerup.realestate.properties.domain.exceptions.CityNonExistentException;
import com.powerup.realestate.properties.domain.exceptions.NeighborhoodNonNullException;
import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.in.CityServicePort;
import com.powerup.realestate.properties.domain.ports.in.LocationServicePort;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.domain.utils.validation.ValidateLocation;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;

import java.util.Optional;


public class LocationUseCase implements LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;
    private final CityServicePort cityServicePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort, CityServicePort cityServicePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityServicePort = cityServicePort;

    }

    @Override
    public void save(LocationModel locationModel) {
        CityEntity cityInfo = locationModel.getCityName();
        String neighborhood = locationModel.getNeighborhood();

        if (neighborhood == null || neighborhood.trim().isEmpty()) {
            throw new NeighborhoodNonNullException();
        }

        if (cityInfo != null && cityInfo.getName() != null && !cityInfo.getName().isEmpty()) {
            CityEntity cityEntity = cityServicePort.findCityByNameIgnoreCaseAndTrim(cityInfo.getName());

            CityEntity cityReference = new CityEntity();
            cityReference.setId(cityEntity.getId());
            locationModel.setCityName(cityReference);

            ValidateLocation.validateIfExistLocation(locationModel, locationPersistencePort);

            locationPersistencePort.save(locationModel);

        } else {
            throw new CityNonExistentException();
        }

    }

    @Override
    public Optional<LocationModel> findByLocationId(Long locationId) {
        if (locationId == null) {
            return Optional.empty();
        }

        return locationPersistencePort.findByLocationId(locationId);
    }

    @Override
    public PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(searchText, page, size, orderAsc);
    }

}