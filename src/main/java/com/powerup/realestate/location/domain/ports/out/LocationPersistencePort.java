package com.powerup.realestate.location.domain.ports.out;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;

import java.util.Optional;


public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    Optional<LocationModel> findByLocationId(Long locationId);
    PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);

}
