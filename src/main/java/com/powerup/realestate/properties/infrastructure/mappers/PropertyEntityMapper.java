package com.powerup.realestate.properties.infrastructure.mappers;


import com.powerup.realestate.location.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring",
        uses = {CategoryEntityMapper.class, LocationEntityMapper.class})
public interface PropertyEntityMapper {

    PropertyEntity modelToEntity(PropertyModel propertyModel);
    PropertyModel entityToModel(PropertyEntity propertyEntity);
    List<PropertyModel> entityListToModelList(List<PropertyEntity> properties);

}
