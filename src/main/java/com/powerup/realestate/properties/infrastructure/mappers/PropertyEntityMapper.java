package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PropertyEntityMapper {
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "categoryId", target = "category")
    PropertyEntity modelToEntity(PropertyModel propertyModel);
    @Mapping(source = "location", target = "locationId")
    @Mapping(source = "category", target = "categoryId")
    PropertyModel entityToModel(PropertyEntity propertyEntity);
    List<PropertyModel> entityListToModelList(List<PropertyEntity> properties);
}
