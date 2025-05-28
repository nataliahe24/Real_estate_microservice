package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VisitScheduleEntityMapper {

    VisitScheduleEntity toEntity(VisitScheduleModel model);
    VisitScheduleModel toModel(VisitScheduleEntity entity);
   List<VisitScheduleModel> toModelList(List<VisitScheduleEntity> entities);
}