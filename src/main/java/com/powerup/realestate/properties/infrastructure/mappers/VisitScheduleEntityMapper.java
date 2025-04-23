package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VisitScheduleEntityMapper {
    
    private final PropertyEntityMapper propertyEntityMapper;
    
    public VisitScheduleEntity toEntity(VisitScheduleModel model) {
        if (model == null) return null;
        
        VisitScheduleEntity entity = new VisitScheduleEntity();
        entity.setId(model.getId());
        entity.setSellerId(model.getSellerId());
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        
        if (model.getProperty() != null) {
            entity.setProperty(propertyEntityMapper.modelToEntity(model.getProperty()));
        }
        
        return entity;
    }
    
    public VisitScheduleModel toModel(VisitScheduleEntity entity) {
        if (entity == null) return null;
        
        return VisitScheduleModel.builder()
                .id(entity.getId())
                .sellerId(entity.getSellerId())
                .property(propertyEntityMapper.entityToModel(entity.getProperty()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
    
    public List<VisitScheduleModel> toModelList(List<VisitScheduleEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
} 