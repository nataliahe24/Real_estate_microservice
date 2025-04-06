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

    @Mapping(source = "locationId", target = "location", qualifiedByName = "mapLocationIdToEntity")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToEntity")
    PropertyModel requestToModel(SavePropertyRequest savePropertyRequest);
    @Mapping(source = "location", target = "locationId", qualifiedByName = "mapLocationEntityToId")
    @Mapping(source = "category", target = "categoryId", qualifiedByName = "mapCategoryEntityToId")
    PropertyResponse modelToResponse(PropertyModel propertyModel);

    @Named("mapCategoryIdToEntity")
    default CategoryEntity mapCategoryIdToEntity(Long id) {
        if (id == null) return null;
        CategoryEntity category = new CategoryEntity();
        category.setId(id);
        return category;
    }

    @Named("mapLocationIdToEntity")
    default LocationEntity mapLocationIdToEntity(Long id) {
        if (id == null) return null;
        LocationEntity location = new LocationEntity();
        location.setId(id);
        return location;
    }

    @Named("mapCategoryEntityToId")
    default Long mapCategoryEntityToId(CategoryEntity category) {
        return category != null ? category.getId() : null;
    }

    @Named("mapLocationEntityToId")
    default Long mapLocationEntityToId(LocationEntity location) {
        return location != null ? location.getId() : null;
    }
}