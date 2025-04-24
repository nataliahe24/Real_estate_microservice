package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.ports.in.BuyerVisitServicePort;
import com.powerup.realestate.properties.domain.ports.out.BuyerVisitPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        // Verificar que el horario existe
        if (!visitSchedulePersistencePort.existsById(buyerVisit.getVisitScheduleId())) {
            throw new PropertyNotFoundException(SCHEDULE_NOT_FOUND);
        }
        
        // Verificar que no se exceda el límite de 2 compradores por horario
        int visitsCount = buyerVisitPersistencePort.countByVisitScheduleId(buyerVisit.getVisitScheduleId());
        if (visitsCount >= 2) {
            throw new IllegalStateException(MAX_VISITORS_EXCEEDED);
        }
        
        // Verificar que el comprador no tenga ya una visita agendada en el mismo horario
        if (buyerVisitPersistencePort.findByBuyerEmailAndVisitScheduleId(
                buyerVisit.getBuyerEmail(), buyerVisit.getVisitScheduleId()).isPresent()) {
            throw new IllegalStateException(BUYER_ALREADY_SCHEDULED);
        }
        
        // Guardar la visita
        buyerVisitPersistencePort.save(buyerVisit);
        
        // Sincronizar el contador de compradores agendados
        syncScheduledBuyersCounter(buyerVisit.getVisitScheduleId());
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
} 