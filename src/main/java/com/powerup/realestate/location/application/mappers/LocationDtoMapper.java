package com.powerup.realestate.location.application.mappers;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.application.dto.response.LocationResponse;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(source = "cityName", target = "cityName", qualifiedByName = "mapCityNameToEntity")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);

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
