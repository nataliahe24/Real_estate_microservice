package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.Optional;


public interface LocationServicePort {
    void save(LocationModel locationModel);
    Optional<LocationModel> findByLocationId(Long locationId);
    PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);
}
