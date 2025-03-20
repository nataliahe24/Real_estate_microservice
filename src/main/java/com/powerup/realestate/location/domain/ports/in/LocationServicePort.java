package com.powerup.realestate.location.domain.ports.in;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;


public interface LocationServicePort {
    void save(LocationModel locationModel);
    PageResult<LocationModel> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);
}
