package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PropertyEntityMapper {
    @Mapping(source = "locationId", target = "location", qualifiedByName = "mapLocationIdToEntity")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToEntity")
    PropertyEntity modelToEntity(PropertyModel propertyModel);
    @Mapping(source = "location", target = "locationId", qualifiedByName = "mapLocationEntityToId")
    @Mapping(source = "category", target = "categoryId", qualifiedByName = "mapCategoryEntityToId")
    PropertyModel entityToModel(PropertyEntity propertyEntity);
    List<PropertyModel> entityListToModelList(List<PropertyEntity> properties);

    @Named("mapLocationIdToEntity")
    default LocationEntity mapLocationIdToEntity(Long locationId) {
        if (locationId == null) {
            return null;
        }
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(locationId);
        return locationEntity;
    }

    @Named("mapCategoryIdToEntity")
    default CategoryEntity mapCategoryIdToEntity(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }

    @Named("mapLocationEntityToId")
    default Long mapLocationEntityToId(LocationEntity locationEntity) {
        return locationEntity != null ? locationEntity.getId() : null;
    }

    @Named("mapCategoryEntityToId")
    default Long mapCategoryEntityToId(CategoryEntity categoryEntity) {
        return categoryEntity != null ? categoryEntity.getId() : null;
    }
}
