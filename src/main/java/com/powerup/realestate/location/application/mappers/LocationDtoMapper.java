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
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(source = "cityId", target = "cityId", qualifiedByName = "mapCityIdToEntity")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
    @Mapping(source = "cityId", target = "cityId", qualifiedByName = "mapCityEntityToId")
    LocationResponse modelToResponse(LocationModel locationModel);
    PageResult<LocationResponse> modelListToResponseList(PageResult<LocationModel> locations);
    @Named("mapCityIdToEntity")
    default CityEntity mapCityIdToEntity(Long cityId) {
        if (cityId == null) {
            return null;
        }
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(cityId);
        return cityEntity;
    }

    @Named("mapCityEntityToId")
    default Long mapCityEntityToId(CityEntity city) {
        return (city != null) ? city.getId() : null;
    }

}
