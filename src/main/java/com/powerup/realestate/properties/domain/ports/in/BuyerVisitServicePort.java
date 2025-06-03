package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;

import java.util.List;

public interface BuyerVisitServicePort {
    void scheduleBuyerVisit(BuyerVisitModel buyerVisit);
    List<BuyerVisitModel> getBuyerVisitsByScheduleId(String buyerEmail);
    void syncScheduledBuyersCounter(Long scheduleId);
    void cancelBuyerVisit(Long visitId);
} 