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
        CityEntity cityInfo = locationModel.getCityName(); // Assuming LocationModel has getCityName()

        if (cityInfo != null && cityInfo.getName() != null && !cityInfo.getName().isEmpty()) {
            try {
                CityEntity cityEntity = cityServicePort.findCityByNameIgnoreCaseAndTrim(cityInfo.getName());

                // Create a reference to the CityEntity to establish the relationship
                CityEntity cityReference = new CityEntity();
                cityReference.setId(cityEntity.getId());
                locationModel.setCityName(cityReference); // Assuming LocationModel has setCity(CityEntity)

                locationPersistencePort.save(locationModel);

            } catch (CityNonExistentException e) {
                throw e; // Re-throw the exception for the calling layer to handle
            }
        } else {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }

    }
    @Override
    public PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(searchText, page, size, orderAsc);
    }

}