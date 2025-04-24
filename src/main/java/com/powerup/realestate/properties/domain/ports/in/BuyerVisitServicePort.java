package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.BuyerVisitModel;

import java.util.List;

public interface BuyerVisitServicePort {
    void scheduleBuyerVisit(BuyerVisitModel buyerVisit);
    List<BuyerVisitModel> getBuyerVisitsByScheduleId(Long scheduleId);
    void syncScheduledBuyersCounter(Long scheduleId);
    void cancelBuyerVisit(Long visitId);
} 