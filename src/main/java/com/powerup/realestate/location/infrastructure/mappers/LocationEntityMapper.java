package com.powerup.realestate.location.infrastructure.mappers;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LocationEntityMapper {
    LocationEntity modelToEntity(LocationModel locationModel);
}
