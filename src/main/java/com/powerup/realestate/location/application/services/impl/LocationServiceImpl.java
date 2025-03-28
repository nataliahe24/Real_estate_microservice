package com.powerup.realestate.location.application.services.impl;

import com.powerup.realestate.location.application.dto.response.LocationResponse;
import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.application.dto.response.SaveLocationResponse;
import com.powerup.realestate.location.application.mappers.LocationDtoMapper;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.application.services.LocationService;
import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationServicePort locationServicePort;
    private final LocationDtoMapper locationDtoMapper;

    @Override
    public SaveLocationResponse save(SaveLocationRequest request) {
        locationServicePort.save(locationDtoMapper.requestToModel(request));
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public PageResult<LocationResponse> getLocations(String searchText,Integer page, Integer size, boolean orderAsc) {
        return locationDtoMapper.modelListToResponseList(locationServicePort.getLocations(searchText, page, size, orderAsc));
    }
}