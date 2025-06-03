package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.application.mappers.BuyerVisitDtoMapper;
import com.powerup.realestate.properties.application.services.BuyerVisitService;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.in.BuyerVisitServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyerVisitServiceImpl implements BuyerVisitService {
    
    private final BuyerVisitServicePort buyerVisitServicePort;
    private final BuyerVisitDtoMapper buyerVisitDtoMapper;
    
    @Autowired
    public BuyerVisitServiceImpl(BuyerVisitServicePort buyerVisitServicePort,
                             BuyerVisitDtoMapper buyerVisitDtoMapper) {
        this.buyerVisitServicePort = buyerVisitServicePort;
        this.buyerVisitDtoMapper = buyerVisitDtoMapper;
    }
    
    @Override
    @Transactional
    public ScheduleBuyerVisitResponse scheduleBuyerVisit(ScheduleBuyerVisitRequest request) {
        BuyerVisitModel buyerVisitModel = buyerVisitDtoMapper.requestToModel(request);
        buyerVisitServicePort.scheduleBuyerVisit(buyerVisitModel);
        return buyerVisitDtoMapper.modelToResponse(buyerVisitModel);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleBuyerVisitResponse> getBuyerVisitsByEmail(String buyerEmail) {
        List<BuyerVisitModel> visits = buyerVisitServicePort.getBuyerVisitsByScheduleId(buyerEmail);
        return buyerVisitDtoMapper.modelListToResponseList(visits);
    }
    
    @Override
    @Transactional
    public void syncScheduledBuyersCounter(Long scheduleId) {
        buyerVisitServicePort.syncScheduledBuyersCounter(scheduleId);
    }
    
    @Override
    @Transactional
    public void cancelBuyerVisit(Long visitId) {
        buyerVisitServicePort.cancelBuyerVisit(visitId);
    }
} 