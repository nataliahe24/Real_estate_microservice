package com.powerup.realestate.location.application.services;


import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;

import com.powerup.realestate.location.application.dto.response.LocationResponse;
import com.powerup.realestate.location.application.dto.response.SaveLocationResponse;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;


public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);
    PageResult<LocationResponse> getLocations(String searchText,Integer page, Integer size, boolean orderAsc);
}
