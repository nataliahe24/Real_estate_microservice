package com.powerup.realestate.location.application.mappers;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.application.dto.response.LocationResponse;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
    LocationResponse modelToResponse(LocationModel locationModel);
    PageResult<LocationResponse> modelListToResponseList(PageResult<LocationModel> locations);

}
