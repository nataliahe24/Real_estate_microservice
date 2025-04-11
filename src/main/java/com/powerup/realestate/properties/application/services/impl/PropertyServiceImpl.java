package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;
import com.powerup.realestate.properties.application.mappers.PropertyDtoMapper;
import com.powerup.realestate.properties.application.services.PropertyService;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;

import com.powerup.realestate.properties.domain.utils.page.PageResult;
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
        propertyServicePort.saveProperty(propertyDtoMapper.requestToModel(request));
        return new SavePropertyResponse(Constants.SAVE_PROPERTY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public PageResult<PropertyResponse> getPropertiesByFiltersAndOrder(Integer page, Integer size, Long location, Long category, Integer rooms, Integer bathrooms, Double minPrice, Double maxPrice, String sortBy, boolean orderAsc) {
        return propertyDtoMapper.modelListToResponseList(
                propertyServicePort.getProperties(
                        page,
                        size,
                        location,
                        category,
                        rooms,
                        bathrooms,
                        minPrice,
                        maxPrice,
                        sortBy,
                        orderAsc)
        );
    }
}
