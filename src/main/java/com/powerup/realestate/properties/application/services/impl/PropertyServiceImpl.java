package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;
import com.powerup.realestate.properties.application.mappers.PropertyDtoMapper;
import com.powerup.realestate.properties.application.services.PropertyService;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyServicePort propertyServicePort;
    private final PropertyDtoMapper propertyDtoMapper;


    @Override
    public SavePropertyResponse save(SavePropertyRequest request) {
        propertyServicePort.save(propertyDtoMapper.requestToModel(request));
        return new SavePropertyResponse(Constants.SAVE_PROPERTY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

}
