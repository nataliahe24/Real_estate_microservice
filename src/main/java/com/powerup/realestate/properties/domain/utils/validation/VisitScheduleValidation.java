package com.powerup.realestate.properties.domain.utils.validation;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitScheduleException;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants;

import java.time.LocalDateTime;

public class VisitScheduleValidation {

    private VisitScheduleValidation() {
        throw new UnsupportedOperationException();
    }

    public static void validateVisitScheduleIsFuture(VisitSchedulePersistencePort visitSchedulePersistencePort, Long scheduleId) {
        visitSchedulePersistencePort.findById(scheduleId).ifPresent(schedule -> {
            LocalDateTime now = LocalDateTime.now();
            if (schedule.getStartDate().isBefore(now)) {
                throw new InvalidVisitScheduleException(VisitScheduleDomainConstants.SCHEDULE_INVALID);
            }
        });
    }
} 