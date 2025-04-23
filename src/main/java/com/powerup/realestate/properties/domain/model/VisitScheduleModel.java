package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitDateException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder
public class VisitScheduleModel {
    @Setter
    private Long id;
    private Long sellerId;
    private PropertyModel property;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    public VisitScheduleModel(Long id, Long sellerId, PropertyModel property, 
                             LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.sellerId = Objects.requireNonNull(sellerId, "El ID del vendedor no puede ser nulo");
        this.property = Objects.requireNonNull(property, "La propiedad no puede ser nula");
        this.startDate = Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
        this.endDate = Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
        
        // Validar que la fecha esté dentro de las próximas 3 semanas
        if (startDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3)) ||
            endDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3))) {
            throw new InvalidVisitDateException();
        }
        
        // Validar que la fecha de fin sea posterior a la de inicio
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
    }
    
    public void setStartDate(LocalDateTime startDate) {
        if (startDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3))) {
            throw new InvalidVisitDateException();
        }
        this.startDate = Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
    }
    
    public void setEndDate(LocalDateTime endDate) {
        if (endDate.toLocalDate().isAfter(LocalDate.now().plusWeeks(3))) {
            throw new InvalidVisitDateException();
        }
        this.endDate = Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
    }
} 