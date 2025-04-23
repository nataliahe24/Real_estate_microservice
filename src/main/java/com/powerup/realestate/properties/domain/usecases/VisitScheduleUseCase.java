package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.UnauthorizedSellerException;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class VisitScheduleUseCase {
    private final VisitSchedulePersistencePort visitSchedulePersistencePort;
    private final PropertyPersistencePort propertyPersistencePort;
    
    public VisitScheduleModel createSchedule(VisitScheduleModel visitSchedule) {
        PropertyModel property = propertyPersistencePort.findById(visitSchedule.getProperty().getId())
                .orElseThrow(() -> new PropertyNotFoundException("Propiedad no encontrada"));

        validateSellerOwnsProperty(visitSchedule.getSellerId(), property);
        
        visitSchedulePersistencePort.save(visitSchedule);
        return visitSchedule;
    }
    
    private void validateSellerOwnsProperty(Long sellerId, PropertyModel property) {
        if (!Objects.equals(sellerId, property.getSellerId())) {
            throw new UnauthorizedSellerException("El vendedor no es propietario de esta propiedad");
        }
    }
    
    public List<VisitScheduleModel> getSchedulesByPropertyId(Long propertyId) {
        return visitSchedulePersistencePort.findByPropertyId(propertyId);
    }
    
    public List<VisitScheduleModel> getSchedulesBySellerId(Long sellerId) {
        return visitSchedulePersistencePort.findBySellerId(sellerId);
    }
    
    public PageResult<VisitScheduleModel> getSchedules(Integer page, Integer size) {
        return visitSchedulePersistencePort.getSchedules(page, size);
    }
    
    public PageResult<VisitScheduleModel> getFilteredSchedules(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String location,
            Integer page,
            Integer size) {
        
        return visitSchedulePersistencePort.getFilteredSchedules(
                startDate,
                endDate,
                location,
                page,
                size
        );
    }
} 