package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.SaveScheduleBuyerResponse;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.application.dto.response.SellerBuyerVisitResponse;
import com.powerup.realestate.properties.application.mappers.BuyerVisitDtoMapper;
import com.powerup.realestate.properties.application.services.BuyerVisitService;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.in.BuyerVisitServicePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerVisitServiceImpl implements BuyerVisitService {

    private final BuyerVisitServicePort buyerVisitServicePort;
    private final BuyerVisitDtoMapper buyerVisitDtoMapper;
    private final VisitSchedulePersistencePort visitSchedulePersistencePort;

    @Override
    @Transactional
    public SaveScheduleBuyerResponse scheduleBuyerVisit(ScheduleBuyerVisitRequest request) {

        VisitScheduleModel visitSchedule = visitSchedulePersistencePort.findById(request.scheduleId())
                .orElseThrow(() -> new RuntimeException(Constants.VISIT_SCHEDULE_NO_FOUND));


        BuyerVisitModel buyerVisitModel = BuyerVisitModel.builder()
                .visitSchedule(visitSchedule)
                .buyerEmail(request.buyerEmail())
                .build();

        buyerVisitServicePort.scheduleBuyerVisit(buyerVisitModel);
        return new SaveScheduleBuyerResponse(
                Constants.SAVE_VISIT_SCHEDULE_RESPONSE_MESSAGE,
                LocalDateTime.now()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleBuyerVisitResponse> getBuyerVisitsByEmail(String buyerEmail) {
        List<BuyerVisitModel> visits = buyerVisitServicePort.getBuyerVisitsByEmail(buyerEmail);
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
    
    @Override
    @Transactional(readOnly = true)
    public List<SellerBuyerVisitResponse> getBuyerVisitsBySellerId(Long sellerId) {
        List<BuyerVisitModel> visits = buyerVisitServicePort.getBuyerVisitsBySellerId(sellerId);
        return visits.stream()
                .map(this::mapToSellerResponse)
                .toList();
    }
    
    private SellerBuyerVisitResponse mapToSellerResponse(BuyerVisitModel model) {
        return new SellerBuyerVisitResponse(
                model.getId(),
                model.getBuyerEmail(),
                model.getVisitSchedule().getId(),
                model.getVisitSchedule().getProperty().getId(),
                model.getVisitSchedule().getProperty().getName(),
                model.getVisitSchedule().getProperty().getAddress(),
                model.getVisitSchedule().getProperty().getLocation().getNeighborhood(),
                model.getVisitSchedule().getProperty().getLocation().getCityName().getName(),
                model.getVisitSchedule().getStartDate(),
                model.getVisitSchedule().getEndDate(),
                LocalDateTime.now()
        );
    }
} 