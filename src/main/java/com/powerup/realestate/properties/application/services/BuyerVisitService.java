package com.powerup.realestate.properties.application.services;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;

import java.util.List;

public interface BuyerVisitService {
    ScheduleBuyerVisitResponse scheduleBuyerVisit(ScheduleBuyerVisitRequest request);
    List<ScheduleBuyerVisitResponse> getBuyerVisitsByEmail(String buyerEmail);
    void syncScheduledBuyersCounter(Long scheduleId);
    void cancelBuyerVisit(Long visitId);
} 