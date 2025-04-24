package com.powerup.realestate.properties.domain.utils.validation;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitScheduleException;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;

import java.time.LocalDateTime;

public class VisitScheduleValidation {

    public static void validateVisitScheduleIsFuture(VisitSchedulePersistencePort visitSchedulePersistencePort, Long scheduleId) {
        visitSchedulePersistencePort.findById(scheduleId)
                .ifPresent(schedule -> {
                    LocalDateTime now = LocalDateTime.now();
                    if (schedule.getStartDate().isBefore(now)) {
                        throw new InvalidVisitScheduleException("No se puede agendar una visita en un horario que ya pasó");
                    }
                });
    }
} 