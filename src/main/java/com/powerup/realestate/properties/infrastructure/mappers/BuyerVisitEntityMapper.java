package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.infrastructure.entities.BuyerVisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuyerVisitEntityMapper {
    
    @Mapping(target = "visitScheduleId", source = "visitSchedule.id")
    BuyerVisitModel toModel(BuyerVisitEntity entity);
    
    @Mapping(target = "visitSchedule", ignore = true)
    BuyerVisitEntity toEntity(BuyerVisitModel model);
    
    List<BuyerVisitModel> toModelList(List<BuyerVisitEntity> entities);
} 