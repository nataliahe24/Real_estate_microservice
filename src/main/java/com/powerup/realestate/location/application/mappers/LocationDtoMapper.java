package com.powerup.realestate.location.application.mappers;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.domain.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
}
