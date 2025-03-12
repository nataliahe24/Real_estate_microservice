package com.powerup.realestate.location.application.mappers;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    SaveLocationRequest requestToModel(SaveLocationRequest saveLocationRequest);
}
