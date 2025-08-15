package com.powerup.realestate.properties.domain.utils.validation;

import com.powerup.realestate.properties.domain.exceptions.LocationAlreadyExist;
import com.powerup.realestate.properties.domain.exceptions.NeighborhoodNonNullException;
import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;

public class ValidateLocation {

    private ValidateLocation() {
        throw new UnsupportedOperationException();
    }

    public static void validateIfExistLocation(LocationModel locationModel, LocationPersistencePort locationPersistencePort) {

        Long cityId = locationModel.getCityName().getId();
        String neighborhood = locationModel.getNeighborhood();

        if (neighborhood == null || neighborhood.trim().isEmpty()) {
            throw new NeighborhoodNonNullException();
        }

        boolean exists = locationPersistencePort.existsByCityIdAndNeighborhoodIgnoreCase(locationModel.getCityName().getId(),
                locationModel.getNeighborhood().trim());

        if (exists) {
            throw new LocationAlreadyExist();
        }
    }
}