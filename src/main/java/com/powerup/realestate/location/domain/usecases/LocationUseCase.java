package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.location.domain.exceptions.DepartmentAlreadyExistsException;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;


public class LocationUseCase implements LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel) {

        if (locationPersistencePort.existsByDepartment(locationModel.getDepartment())) {
            throw new DepartmentAlreadyExistsException();
        }

        locationPersistencePort.save(locationModel);
    }
    @Override
    public PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(searchText, page, size, orderAsc);
    }

}