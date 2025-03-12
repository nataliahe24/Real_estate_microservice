package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.location.domain.exceptions.DeparmentAlreadyExistsException;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;

import java.util.List;

public class LocationUseCase implements LocationPersistencePort {
    private final LocationPersistencePort locationPersistencePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel) {

        locationPersistencePort.save(locationModel);
    }

}