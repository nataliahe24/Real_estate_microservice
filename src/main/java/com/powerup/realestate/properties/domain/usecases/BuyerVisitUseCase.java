package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitScheduleException;
import com.powerup.realestate.properties.domain.exceptions.MaxVisistException;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.ScheduleNotFountExceptions;
import com.powerup.realestate.properties.domain.exceptions.BuyerEmailNotNullException;
import com.powerup.realestate.properties.domain.exceptions.ScheduleIdNotNullException;
import com.powerup.realestate.properties.domain.exceptions.InvalidEmailFormatException;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
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
        // Verificar que scheduleId no sea nulo
        Long scheduleId = buyerVisit.getVisitScheduleId();
        if (scheduleId == null) {
            throw new ScheduleIdNotNullException(FIELD_VISIT_SCHEDULE_ID_NULL_MESSAGE);
        }

        // Verificar que buyerEmail no sea nulo
        String buyerEmail = buyerVisit.getBuyerEmail();
        if (buyerEmail == null || buyerEmail.trim().isEmpty()) {
            throw new BuyerEmailNotNullException(FIELD_BUYER_EMAIL_NULL_MESSAGE);
        }
        
        // Validar formato de email
        validateEmailFormat(buyerEmail);

        // Verificar que el horario existe
        if (!visitSchedulePersistencePort.existsById(scheduleId)) {
            throw new ScheduleNotFountExceptions(SCHEDULE_NOT_FOUND);
        }
        
        // Verificar que el horario no haya pasado ya
        visitSchedulePersistencePort.findById(scheduleId)
            .ifPresent(schedule -> {
                LocalDateTime now = LocalDateTime.now();
                if (schedule.getStartDate().isBefore(now)) {
                    throw new InvalidVisitScheduleException(PAST_SCHEDULE_ERROR);
                }
            });
        
        // Verificar que no se exceda el límite de 2 compradores por horario
        int visitsCount = buyerVisitPersistencePort.countByVisitScheduleId(scheduleId);
        if (visitsCount >= 2) {
            throw new MaxVisistException(MAX_VISITORS_EXCEEDED);
        }
        
        // Verificar que el comprador no tenga ya una visita agendada en el mismo horario
        if (buyerVisitPersistencePort.findByBuyerEmailAndVisitScheduleId(
                buyerEmail, scheduleId).isPresent()) {
            throw new MaxVisistException(BUYER_ALREADY_SCHEDULED);
        }
        
        // Guardar la visita
        buyerVisitPersistencePort.save(buyerVisit);
        
        // Sincronizar el contador de compradores agendados
        syncScheduledBuyersCounter(scheduleId);
    }
    
    @Override
    public List<BuyerVisitModel> getBuyerVisitsByScheduleId(Long scheduleId) {
        return buyerVisitPersistencePort.findByVisitScheduleId(scheduleId);
    }
    
    @Override
    public void syncScheduledBuyersCounter(Long scheduleId) {
        int actualCount = buyerVisitPersistencePort.countByVisitScheduleId(scheduleId);
        visitSchedulePersistencePort.updateScheduledBuyersCount(scheduleId, actualCount);
    }
    
    @Override
    public void cancelBuyerVisit(Long visitId) {
        buyerVisitPersistencePort.findById(visitId).ifPresent(visit -> {
            Long scheduleId = visit.getVisitScheduleId();
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