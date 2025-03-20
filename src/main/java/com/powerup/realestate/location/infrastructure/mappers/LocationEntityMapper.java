package com.powerup.realestate.location.infrastructure.mappers;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LocationEntityMapper {
    LocationEntity modelToEntity(LocationModel locationModel);
    LocationModel entityToModel(LocationEntity locationEntity);
    List<LocationModel> entityListToModelList(List<LocationEntity> locations);
}
