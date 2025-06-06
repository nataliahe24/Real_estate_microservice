package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.infrastructure.entities.BuyerVisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {VisitScheduleEntityMapper.class})
public interface BuyerVisitEntityMapper {
    
    @Mapping(target = "visitSchedule.id", source = "visitSchedule.id")
    @Mapping(target = "visitSchedule.startDate", source = "visitSchedule.startDate")
    @Mapping(target = "visitSchedule.endDate", source = "visitSchedule.endDate")
    BuyerVisitModel toModel(BuyerVisitEntity entity);
    
    @Mapping(target = "visitSchedule.id", source = "visitSchedule.id")
    @Mapping(target = "visitSchedule.startDate", source = "visitSchedule.startDate")
    @Mapping(target = "visitSchedule.endDate", source = "visitSchedule.endDate")
    BuyerVisitEntity toEntity(BuyerVisitModel model);
    
    List<BuyerVisitModel> toModelList(List<BuyerVisitEntity> entities);
} 