package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PropertyDtoMapper {

    PropertyModel  requestToModel(SavePropertyRequest savePropertyRequest);
    @Mapping(source = "locationId", target = "locationId")
    @Mapping(source = "categoryId", target = "categoryId")
    PropertyResponse modelToResponse(PropertyModel propertyModel);
}