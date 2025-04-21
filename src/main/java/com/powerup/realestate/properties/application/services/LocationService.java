package com.powerup.realestate.properties.application.services;


import com.powerup.realestate.properties.application.dto.request.SaveLocationRequest;

import com.powerup.realestate.properties.application.dto.response.LocationResponse;
import com.powerup.realestate.properties.application.dto.response.SaveLocationResponse;
import com.powerup.realestate.properties.domain.utils.page.PageResult;


public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);
    PageResult<LocationResponse> getLocations(String searchText,Integer page, Integer size, boolean orderAsc);
}
