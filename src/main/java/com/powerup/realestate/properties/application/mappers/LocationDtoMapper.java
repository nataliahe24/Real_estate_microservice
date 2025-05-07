package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.properties.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.properties.application.dto.response.LocationResponse;
import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(source = "cityName", target = "cityName", qualifiedByName = "mapCityNameToEntity")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);

    @Mapping(source = "cityName.departmentEntity.name", target = "department")
    @Mapping(source = "cityName", target = "cityName", qualifiedByName = "mapCityEntityToName")
    LocationResponse modelToResponse(LocationModel locationModel);
    PageResult<LocationResponse> modelListToResponseList(PageResult<LocationModel> locations);

    default LocationModel toLocationModel(Long id) {
        return LocationModel.builder().id(id).build();
    }

    default Long getIdFromLocationModel(LocationModel locationModel) {
        return locationModel.getId();
    }

    @Named("mapCityNameToEntity")
    default CityEntity mapCityNameToEntity(String cityName) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);
        return cityEntity;
    }

    @Named("mapCityEntityToName")
    default String mapCityEntityToName(CityEntity city) {
        return city.getName();
    }

}
