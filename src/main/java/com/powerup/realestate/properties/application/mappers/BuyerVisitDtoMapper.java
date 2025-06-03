package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
         uses = {VisitScheduleDtoMapper.class})

public interface BuyerVisitDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitScheduleId", source = "scheduleId")
    BuyerVisitModel requestToModel(ScheduleBuyerVisitRequest request);
    
    @Mapping(target = "scheduleId", source = "visitScheduleId")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    ScheduleBuyerVisitResponse modelToResponse(BuyerVisitModel model);
    
    List<ScheduleBuyerVisitResponse> modelListToResponseList(List<BuyerVisitModel> models);
} 