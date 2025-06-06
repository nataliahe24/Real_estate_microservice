package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.properties.application.dto.request.VisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.response.VisitScheduleResponse;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitScheduleDtoMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    VisitScheduleModel requestToModel(VisitScheduleRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    VisitScheduleResponse modelToResponse(VisitScheduleModel model);

    List<VisitScheduleResponse> modelListToResponseList(List<VisitScheduleModel> models);
}