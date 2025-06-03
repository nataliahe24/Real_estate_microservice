package com.powerup.realestate.properties.application.services;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;

import java.util.List;

public interface BuyerVisitService {
    ScheduleBuyerVisitResponse scheduleBuyerVisit(ScheduleBuyerVisitRequest request);
    List<ScheduleBuyerVisitResponse> getBuyerVisitsByScheduleId(String buyerEmail);
    void syncScheduledBuyersCounter(Long scheduleId);
    void cancelBuyerVisit(Long visitId);
} 