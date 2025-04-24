package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.BuyerVisitModel;

import java.util.List;
import java.util.Optional;

public interface BuyerVisitPersistencePort {
    void save(BuyerVisitModel buyerVisit);
    List<BuyerVisitModel> findByVisitScheduleId(Long visitScheduleId);
    Optional<BuyerVisitModel> findByBuyerEmailAndVisitScheduleId(String buyerEmail, Long visitScheduleId);
    int countByVisitScheduleId(Long visitScheduleId);
    Optional<BuyerVisitModel> findById(Long visitId);
    void delete(Long visitId);
} 