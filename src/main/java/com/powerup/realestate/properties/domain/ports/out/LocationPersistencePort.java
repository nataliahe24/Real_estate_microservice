package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.Optional;


public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    Optional<LocationModel> findByLocationId(Long locationId);
    PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);
    boolean existsByCityIdAndNeighborhoodIgnoreCase(Long cityId, String neighborhood);
}
