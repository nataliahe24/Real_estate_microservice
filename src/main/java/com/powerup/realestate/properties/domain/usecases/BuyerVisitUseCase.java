package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.*;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.in.BuyerVisitServicePort;
import com.powerup.realestate.properties.domain.ports.out.BuyerVisitPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDateTime;


import static com.powerup.realestate.properties.domain.utils.constants.BuyerVisitDomainConstants.*;

@Component
public class BuyerVisitUseCase implements BuyerVisitServicePort {
    
    private final BuyerVisitPersistencePort buyerVisitPersistencePort;
    private final VisitSchedulePersistencePort visitSchedulePersistencePort;
    
    @Autowired
    public BuyerVisitUseCase(BuyerVisitPersistencePort buyerVisitPersistencePort,
                             VisitSchedulePersistencePort visitSchedulePersistencePort) {
        this.buyerVisitPersistencePort = buyerVisitPersistencePort;
        this.visitSchedulePersistencePort = visitSchedulePersistencePort;
    }
    
    @Override
    public void scheduleBuyerVisit(BuyerVisitModel buyerVisit) {

        Long scheduleId = buyerVisit.getVisitSchedule().getId();
        if (scheduleId == null) {
            throw new ScheduleIdNotNullException(FIELD_VISIT_SCHEDULE_ID_NULL_MESSAGE);
        }


        String buyerEmail = buyerVisit.getBuyerEmail();
        if (buyerEmail == null || buyerEmail.trim().isEmpty()) {
            throw new BuyerEmailNotNullException(FIELD_BUYER_EMAIL_NULL_MESSAGE);
        }
        

        validateEmailFormat(buyerEmail);


        if (!visitSchedulePersistencePort.existsById(scheduleId)) {
            throw new ScheduleNotFountExceptions(SCHEDULE_NOT_FOUND);
        }
        

        visitSchedulePersistencePort.findById(scheduleId)
            .ifPresent(schedule -> {
                LocalDateTime now = LocalDateTime.now();
                if (schedule.getStartDate().isBefore(now)) {
                    throw new InvalidVisitScheduleException(PAST_SCHEDULE_ERROR);
                }
            });

        if (buyerVisitPersistencePort.findByBuyerEmailAndVisitScheduleId(
                buyerEmail, scheduleId).isPresent()) {
            throw new VisitAlreadyScheduled(BUYER_ALREADY_SCHEDULED);
        }
        

        int visitsCount = buyerVisitPersistencePort.countByVisitScheduleId(scheduleId);
        if (visitsCount >= 2) {
            throw new MaxVisitException(MAX_VISITORS_EXCEEDED);
        }


        buyerVisitPersistencePort.save(buyerVisit);
        

        syncScheduledBuyersCounter(scheduleId);
    }
    
    @Override
    public List<BuyerVisitModel> getBuyerVisitsByScheduleId(String buyerEmail) {
        return buyerVisitPersistencePort.findByVisitScheduleId(buyerEmail);
    }
    
    @Override
    public void syncScheduledBuyersCounter(Long scheduleId) {
        int actualCount = buyerVisitPersistencePort.countByVisitScheduleId(scheduleId);
        visitSchedulePersistencePort.updateScheduledBuyersCount(scheduleId, actualCount);
    }
    
    @Override
    public void cancelBuyerVisit(Long visitId) {
        buyerVisitPersistencePort.findById(visitId).ifPresent(visit -> {
            Long scheduleId = visit.getId();
            buyerVisitPersistencePort.delete(visitId);
            syncScheduledBuyersCounter(scheduleId);
        });

    }

        private void validateEmailFormat(String email) {
            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT_MESSAGE);
            }

    }
} 