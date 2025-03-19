package com.powerup.realestate.location.domain.ports.out;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;


public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    boolean existsByDepartment(String department);
    PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);

}
