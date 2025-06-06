package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitDateException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;
import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.*;

@Getter
@Builder
public class VisitScheduleModel {
    @Setter
    private Long id;
    private Long sellerId;
    private PropertyModel property;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Setter
    private Integer scheduledBuyers;
    
    public VisitScheduleModel(Long id, Long sellerId, PropertyModel property, 
                             LocalDateTime startDate, LocalDateTime endDate, Integer scheduledBuyers) {
        this.id = id;
        this.sellerId = sellerId;
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduledBuyers = scheduledBuyers != null ? scheduledBuyers : 0;

    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = Objects.requireNonNull(startDate, FIELD_START_DATE_NULL_MESSAGE);
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = Objects.requireNonNull(endDate, FIELD_END_DATE_NULL_MESSAGE);
    }
} 