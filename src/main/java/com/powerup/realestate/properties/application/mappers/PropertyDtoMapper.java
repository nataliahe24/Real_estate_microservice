package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.location.application.mappers.LocationDtoMapper;
import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryDtoMapper.class, LocationDtoMapper.class})
public interface PropertyDtoMapper {

    PropertyModel  requestToModel(SavePropertyRequest savePropertyRequest);
    PropertyResponse modelToResponse(PropertyModel propertyModel);
}