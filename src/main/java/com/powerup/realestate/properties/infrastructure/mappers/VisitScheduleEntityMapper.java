package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitScheduleEntityMapper {
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    VisitScheduleModel toModel(VisitScheduleEntity entity);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    VisitScheduleEntity toEntity(VisitScheduleModel model);
    
    List<VisitScheduleModel> toModelList(List<VisitScheduleEntity> entities);
}