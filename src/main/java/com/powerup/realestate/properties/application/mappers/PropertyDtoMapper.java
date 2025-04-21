package com.powerup.realestate.properties.application.mappers;


import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryDtoMapper.class, LocationDtoMapper.class})
public interface PropertyDtoMapper {

    PropertyModel  requestToModel(SavePropertyRequest savePropertyRequest);
    @Mapping(source = "location.neighborhood", target = "neighborhood")
    @Mapping(source = "location.cityName.name", target = "city")
    @Mapping(source = "location.cityName.departmentEntity.name", target = "department")
    @Mapping(source = "category.name", target = "category")
    PropertyResponse modelToResponse(PropertyModel propertyModel);
    PageResult<PropertyResponse> modelListToResponseList(PageResult<PropertyModel> properties);
}