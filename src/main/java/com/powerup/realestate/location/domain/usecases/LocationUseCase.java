package com.powerup.realestate.location.domain.usecases;


import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;


public class LocationUseCase implements LocationPersistencePort, LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel) {

        locationPersistencePort.save(locationModel);
    }
}