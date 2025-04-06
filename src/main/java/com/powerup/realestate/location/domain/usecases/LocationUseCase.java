package com.powerup.realestate.location.domain.usecases;


import com.powerup.realestate.location.domain.exceptions.CityNonExistentException;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.in.CityServicePort;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;


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

        if (cityInfo != null && cityInfo.getName() != null && !cityInfo.getName().isEmpty()) {
            CityEntity cityEntity = cityServicePort.findCityByNameIgnoreCaseAndTrim(cityInfo.getName());

            CityEntity cityReference = new CityEntity();
            cityReference.setId(cityEntity.getId());
            locationModel.setCityName(cityReference);

            locationPersistencePort.save(locationModel);

        } else {
            throw new CityNonExistentException();
        }

    }
    @Override
    public PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(searchText, page, size, orderAsc);
    }

}