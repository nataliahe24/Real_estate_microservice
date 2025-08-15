package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.*;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.domain.utils.validation.VisitScheduleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.*;

@Component
@RequiredArgsConstructor
public class VisitScheduleUseCase {
    private final VisitSchedulePersistencePort visitSchedulePersistencePort;
    private final PropertyPersistencePort propertyPersistencePort;

    public VisitScheduleModel createSchedule(VisitScheduleModel visitSchedule) {
        PropertyModel property = propertyPersistencePort.findById(visitSchedule.getProperty().getId())
                .orElseThrow(() -> new PropertyNotFoundException(PROPERTY_NOT_FOUND_MESSAGE));

        validateSellerOwnsProperty(visitSchedule.getSellerId(), property);
        validateVisitDates(visitSchedule.getStartDate(), visitSchedule.getEndDate());
        validateSchedule(property, visitSchedule.getStartDate(), visitSchedule.getEndDate());
        VisitScheduleValidation.validateVisitScheduleIsFuture(visitSchedulePersistencePort,visitSchedule.getId());
        
        visitSchedulePersistencePort.save(visitSchedule);
        return visitSchedule;
    }
    
    private void validateSellerOwnsProperty(Long sellerId, PropertyModel property) {
        if (!Objects.equals(sellerId, property.getSellerId())) {
            throw new UnauthorizedSellerException(UNAUTHORIZED_SELLER_MESSAGE);
        }
    }

    private void validateVisitDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3)) ||
            endDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3))) {
            throw new InvalidVisitDateException();
        }
        
        if (endDate.isBefore(startDate)) {
            throw new InvalidEndDateException(INVALID_END_DATE_MESSAGE);
        }
    }

    private void validateSchedule(PropertyModel property, LocalDateTime startDate, LocalDateTime endDate) {
        boolean existsConflict = visitSchedulePersistencePort.existsByPropertyAndScheduleOverlap(
                property.getId(), startDate, endDate
        );

        if (existsConflict) {
            throw new ScheduleConflictException(SCHEDULE_ALL_EXIST);
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